package com.spring.postme.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class PostFile {
	private Integer id;
	private Integer userId;
	private Integer postId;
	private String filename;
	private String filepath;
	private long filesize;

	@Builder
	public PostFile(Integer id, Integer userId, Integer postId, String filename, String filepath, long filesize) {
		this.id = id;
		this.userId = userId;
		this.postId = postId;
		this.filename = filename;
		this.filepath = filepath;
		this.filesize = filesize;
	}
}
