package com.spring.postme.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Post {

	private Integer id;
	private Integer userId;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

	@Builder
	public Post(Integer id, Integer noticeNum, Integer userId, String title, String content, LocalDateTime createdAt,
			LocalDateTime modifiedAt) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

}
