package com.chatapp.models;

public class Friend {
    private String sender; // Người gửi lời mời kết bạn
    private String receiver; // Người nhận lời mời kết bạn
    private String owner; // Chủ sở hữu của mối quan hệ
    private boolean status; // Trạng thái của mối quan hệ (chấp nhận hoặc từ chối)

    // Constructors
    public Friend() {
        // Constructor mặc định không tham số
    }

    public Friend(String sender, String receiver, String owner, boolean status) {
        // Constructor với các tham số
        this.sender = sender;
        this.receiver = receiver;
        this.owner = owner;
        this.status = status;
    }

    // Getters và setters
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
