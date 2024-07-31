package com.derek.email.vo;

/**
 * @Author Derek Zhu
 * @Description Email Value Object class to hold email details.
 * @Date 2024/07/19:14
 */
public class EmailVo {

    private String subject;
    private String content;
    private String recipientAddress;
    private Long userId;

    // Constructors
    public EmailVo() {}

    public EmailVo(String subject, String content, String recipientAddress) {
        this.subject = subject;
        this.content = content;
        this.recipientAddress = recipientAddress;
    }

    // Getters and Setters
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "EmailVo{" +
                "subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", recipientAddress='" + recipientAddress + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}

