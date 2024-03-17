package com.spring.postme.controller.user;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.postme.model.PostFile;
import com.spring.postme.service.user.PostFileService;

@Controller
public class PostFileController {

	@Autowired
	private PostFileService fileService;

	@GetMapping(value = "/download/file/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable int fileId) {

		PostFile attachmentFile = null;
		Resource resource = null;
		try {
			attachmentFile = fileService.getAttachmentFileByFileId(fileId);
			Path path = Paths.get(attachmentFile.getFilepath() + "\\" + attachmentFile.getFilename());
			resource = new InputStreamResource(Files.newInputStream(path));
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDisposition(
				ContentDisposition.builder("attachment").filename(attachmentFile.getFilename()).build());

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping(value = "/file/{fileId}")
	public String deleteFileByFileNo(@PathVariable int fileId) throws Exception {
		boolean result = false;
		String resultCode = "";

		result = fileService.deleteAttachmentFileByFileNo(fileId);
		System.out.println(result);
		if (result) {
			System.out.println("성공");
			resultCode = "S000";
		} else {
			System.out.println("실패");
			resultCode = "F000";
		}

		return resultCode;
	}

}
