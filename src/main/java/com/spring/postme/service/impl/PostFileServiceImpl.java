package com.spring.postme.service.impl;

import java.sql.SQLException;

import org.springframework.web.multipart.MultipartFile;

import com.spring.postme.model.PostFile;

public interface PostFileServiceImpl {

	PostFile getAttachmentFileByFileId(int fileId) throws SQLException, Exception;

	boolean insertAttachmentFile(MultipartFile file, int postId, int userId) throws SQLException, Exception;

	boolean deleteAttachmentFileByFileNo(int fileId) throws SQLException, Exception;

}
