package com.chatapp.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatapp.services.FileServiceAbstract;
import com.chatapp.services.impl.FileService;

@WebServlet("/files/*")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Tạo một instance của FileServiceAbstract, được triển khai bởi FileService
	private FileServiceAbstract fileService = FileService.getInstance();

	public FileController() {
		super();
	}

	// Xử lý các yêu cầu GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedFile = request.getPathInfo();
		if (requestedFile == null) {
			// Nếu không có tệp được yêu cầu, trả về mã lỗi 404
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			// Lấy đường dẫn gốc của tệp
			String filePath = FileServiceAbstract.rootLocation.toString();
			// Tạo đối tượng File từ đường dẫn và tệp được yêu cầu sau khi giải mã
			File file = new File(filePath, URLDecoder.decode(requestedFile, "UTF-8"));
			if (!file.exists()) {
				// Nếu tệp không tồn tại, trả về mã lỗi 404
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} else {
				// Lấy loại nội dung (MIME type) của tệp
				String contentType = getServletContext().getMimeType(file.getName());
				// Gọi phương thức để xử lý và gửi tệp tới client
				fileService.handleStreamFileToClient(file, contentType, response);
			}
		}
	}
}
