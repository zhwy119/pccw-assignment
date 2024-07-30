document.addEventListener('DOMContentLoaded', function () {
    const userForm = document.getElementById('userForm');
    const usersTableBody = document.querySelector('#usersTable tbody');
    const emailsTableBody = document.querySelector('#emailsTable tbody');
    const saveUserButton = document.getElementById('saveUserButton');
    const loadingOverlay = document.getElementById('loadingOverlay');
    const confirmDeleteButton = document.getElementById('confirmDeleteButton');

    let users = [];
    let emails = [];
    let editIndex = -1;
    let deletingIndex = -1; // 记录正在删除的用户索引

    async function loadUsers() {
        try {
            loadingOverlay.style.display = 'flex';
            const response = await axios.get('/user/users');
            users = response.data.object;
            renderUsers();
        } catch (error) {
            console.error('Error loading users:', error);
        } finally {
            loadingOverlay.style.display = 'none';
        }
    }

    async function loadEmails() {
        try {
            loadingOverlay.style.display = 'flex';
            // Load emails from server (you need to implement this API)
            const response = await axios.get('/email/emails');
            emails = response.data.object;
            renderEmails();
        } catch (error) {
            console.error('Error loading emails:', error);
        } finally {
            loadingOverlay.style.display = 'none';
        }
    }

    function renderUsers() {
        usersTableBody.innerHTML = '';
        users.forEach((user, index) => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${user.userName}</td>
                <td>${user.mobile}</td>
                <td>${user.email}</td>
                <td>
                    <button class="btn btn-warning btn-sm" onclick="editUser(${index})">Edit</button>
                    <button class="btn btn-danger btn-sm" onclick="confirmDeleteUser(${index})">Delete</button>
                </td>
            `;
            usersTableBody.appendChild(row);
        });
    }

    function renderEmails() {
        emailsTableBody.innerHTML = '';
        emails.forEach(email => {
            const row = document.createElement('tr');
            const sentStatusText = email.sentStatus === 1 ? 'Sent successfully' : 'Sent failed';
            row.innerHTML = `
                <td>${email.subject}</td>
                <td>${email.content}</td>
                <td>${email.recipientAddress}</td>
                <td>${email.createDate}</td>
                <td>${sentStatusText}</td>
            `;
            emailsTableBody.appendChild(row);
        });
    }

    saveUserButton.addEventListener('click', async function () {
        saveUserButton.disabled = true;  // 禁用按钮
        if (userForm.checkValidity() === false) {
            userForm.classList.add('was-validated');
            saveUserButton.disabled = false;
            return;
        }
        const userName = document.getElementById('userName').value;
        const mobile = document.getElementById('mobile').value;
        const email = document.getElementById('email').value;
        const user = { userName, mobile, email };

        try {
            if (editIndex === -1) {
                await axios.post('/user/users', user);
            } else {
                await axios.put(`/user/users/${users[editIndex].id}`, user);
                editIndex = -1;
            }
            await loadUsers();
            $('#userModal').modal('hide');
            userForm.reset();
            userForm.classList.remove('was-validated');
        } catch (error) {
            console.error('Error saving user:', error);
        } finally {
            saveUserButton.disabled = false;  // 重新启用按钮
        }
    });

    window.editUser = function (index) {
        const user = users[index];
        document.getElementById('userName').value = user.userName;
        document.getElementById('mobile').value = user.mobile;
        document.getElementById('email').value = user.email;
        editIndex = index;
        $('#userModal').modal('show');
    };

    window.confirmDeleteUser = function (index) {
        deletingIndex = index;
        $('#confirmDeleteModal').modal('show'); // 显示确认删除对话框
    };

    confirmDeleteButton.addEventListener('click', async function () {
        confirmDeleteButton.disabled = true;
        await deactivateUser(deletingIndex);
        confirmDeleteButton.disabled = true;
        $('#confirmDeleteModal').modal('hide');
    });

    async function deactivateUser(index) {

        try {
            loadingOverlay.style.display = 'flex';
            await axios.delete(`/user/users/${users[index].id}`);
            await loadUsers();
        } catch (error) {
            console.error('Error deactivating user:', error);
        } finally {
            loadingOverlay.style.display = 'none';
        }
    }

    $('#userModal').on('show.bs.modal', function () {
        if (editIndex === -1) {
            userForm.reset();
            userForm.classList.remove('was-validated');
        }
    });

    loadUsers();
    loadEmails();
});
