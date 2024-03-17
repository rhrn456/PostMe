package com.spring.postme.mapper;

import com.spring.postme.model.PostFile;

public interface PostFileMapper {

    // Create
    int insertAttachmentFile(PostFile attachmentFile);

    // Read
    PostFile getAttachmentFileByFileId(int fileId);
    PostFile getAttachmentFileByPostId(int postId);

    // Delete
    int deleteAttachmentFileByFileId(int fileId);
    int deleteByPostId(Integer postId);

}
