package com.chatapp.models.dtos;

import java.io.FileOutputStream;

public class FileDTO {
    // Tên của tệp
    private String filename;
    // Loại của tệp (ví dụ: "image", "document", vv)
    private String typeFile;
    // Đối tượng FileOutputStream để ghi dữ liệu vào tệp
    private FileOutputStream fileOutputStream;
    // Người gửi tệp
    private String sender;
    // Người nhận tệp
    private String receiver;
    // ID của nhóm nếu tệp thuộc về một cuộc trò chuyện nhóm
    private Long groupId;
    // URL của tệp nếu được lưu trữ trên một máy chủ hoặc nơi khác trên internet
    private String url;

    // Constructor để khởi tạo một đối tượng FileDTO với các thông tin tệp được cung cấp
    public FileDTO(String filename, String typeFile, FileOutputStream fileOutputStream, String sender, String receiver,
            Long groupId, String url) {
        this.filename = filename;
        this.typeFile = typeFile;
        this.fileOutputStream = fileOutputStream;
        this.sender = sender;
        this.receiver = receiver;
        this.groupId = groupId;
        this.url = url;
    }

    // Phương thức getter và setter cho thuộc tính 'groupId'
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    // Phương thức getter và setter cho thuộc tính 'filename'
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    // Phương thức getter và setter cho thuộc tính 'typeFile'
    public String getTypeFile() {
        return typeFile;
    }

    public void setTypeFile(String typeFile) {
        this.typeFile = typeFile;
    }

    // Phương thức getter và setter cho thuộc tính 'fileOutputStream'
    public FileOutputStream getFileOutputStream() {
        return fileOutputStream;
    }

    public void setFileOutputStream(FileOutputStream fileOutputStream) {
        this.fileOutputStream = fileOutputStream;
    }

    // Phương thức getter và setter cho thuộc tính 'sender'
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    // Phương thức getter và setter cho thuộc tính 'receiver'
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    // Phương thức getter và setter cho thuộc tính 'url'
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
