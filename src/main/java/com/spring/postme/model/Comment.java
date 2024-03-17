package com.spring.postme.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	private Integer id;
	private Integer postId;
	private Integer userId;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
}