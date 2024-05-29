package com.chatapp.services.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.chatapp.services.FileServiceAbstract;

//Lớp FileService kế thừa từ FileServiceAbstract
public class FileService extends FileServiceAbstract {
 // Biến instance dùng cho Singleton Pattern
 private static FileService instance = null;

 // Constructor private để ngăn chặn việc khởi tạo trực tiếp từ bên ngoài
 private FileService() {
 }

 // Phương thức getInstance để lấy instance duy nhất của lớp FileService
 public static FileService getInstance() {
     if (instance == null) {
         instance = new FileService();
     }
     return instance;
 }

 // Ghi đè phương thức handleStreamFileToClient từ lớp cha để xử lý việc gửi file tới client
 @Override
 public void handleStreamFileToClient(File file, String contentType, HttpServletResponse response) {
	    // Nếu contentType là null, đặt giá trị mặc định là "application/octet-stream"
	    if (contentType == null) {
	        contentType = "application/octet-stream";
	    }

	    // Reset response để đảm bảo không có dữ liệu nào được gửi trước đó
	    response.reset();
	    response.setBufferSize(DEFAULT_BUFFER_SIZE);  // Đặt kích thước buffer mặc định cho response
	    response.setContentType(contentType);  // Đặt loại nội dung cho response
	    response.setHeader("Content-Length", String.valueOf(file.length()));  // Đặt độ dài của tệp trong header
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");  // Đặt tiêu đề phân phối để tệp được tải xuống với tên gốc

	    BufferedInputStream input = null;
	    BufferedOutputStream output = null;
	    try {
	        // Mở input stream từ file với buffer kích thước mặc định
	        input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
	        // Mở output stream từ response với buffer kích thước mặc định
	        output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

	        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
	        int length;
	        // Đọc dữ liệu từ input stream và ghi vào output stream cho đến khi kết thúc tệp
	        while ((length = input.read(buffer)) > 0) {
	            output.write(buffer, 0, length);
	        }
	    } catch (IOException ex) {
	        ex.printStackTrace();  // In lỗi ra console nếu có ngoại lệ xảy ra
	    } finally {
	        try {
	            // Đóng output stream nếu nó không null
	            if (output != null) {
	                output.close();
	            }
	            // Đóng input stream nếu nó không null
	            if (input != null) {
	                input.close();
	            }
	        } catch (IOException ex) {
	            // Log hoặc xử lý ngoại lệ khi đóng stream
	            ex.printStackTrace();
	        }
	    }
	}

}