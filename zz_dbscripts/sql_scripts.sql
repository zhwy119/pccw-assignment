create table user
(
    id          int auto_increment comment 'Primary Key ID'
        primary key,
    user_name   varchar(50)                          not null comment 'Username',
    mobile      varchar(15)                          not null comment 'Mobile Number',
    email       varchar(100)                         not null comment 'Email Address',
    create_date timestamp  default CURRENT_TIMESTAMP not null comment 'Creation Date',
    update_date timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment 'Update Date',
    del_flag    varchar(1) default '0'               null comment 'Deletion Flag (0 for not deleted, 1 for deleted)'
)
    comment 'User Table';

create table email
(
    id                bigint auto_increment comment 'Primary key ID'
        primary key,
    subject           varchar(255)                         not null comment 'Subject of the email',
    content           text                                 not null comment 'Content of the email',
    recipient_address varchar(255)                         not null comment 'Recipient email address',
    user_id           bigint                               not null,
    create_date       datetime   default CURRENT_TIMESTAMP not null comment 'Creation date',
    update_date       datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment 'Update date',
    del_flag          varchar(1) default '0'               null comment 'Deletion flag (0: not deleted, 1: deleted)'
)
    comment 'Email table';




