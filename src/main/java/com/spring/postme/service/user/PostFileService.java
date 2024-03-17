package com.spring.postme.service.user;

import java.io.File;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.spring.postme.mapper.PostFileMapper;
import com.spring.postme.model.PostFile;
import com.spring.postme.service.impl.PostFileServiceImpl;

@Service
public class PostFileService implements PostFileServiceImpl {
    
    @Autowired
    PostFileMapper attachmentFileMapper;

    public PostFile getAttachmentFileByFileId(int fileId) throws SQLException, Exception {
        return attachmentFileMapper.getAttachmentFileByFileId(fileId);
    }

    public PostFile getAttachmentFileByPostId(int postId) {
        return attachmentFileMapper.getAttachmentFileByPostId(postId);
    }

    public boolean insertAttachmentFile(MultipartFile file, int postId, int userId) {
        if (file.isEmpty()) {
            return false;
        }

        String filePath = "C:\\Users\\user\\Desktop\\Boardproject1\\filetest";
        String attachmentOriginalFileName = file.getOriginalFilename();
        Long attachmentFileSize = file.getSize();

        PostFile attachmentFile = PostFile.builder()
                .postId(postId)
                .userId(userId)
                .filename(attachmentOriginalFileName)
                .filesize(attachmentFileSize)
                .filepath(filePath)
                .build();

        int res = attachmentFileMapper.insertAttachmentFile(attachmentFile);
        return res != 0;
    }

    @Transactional
    public boolean deleteAttachmentFileByFileNo(int fileId) throws Exception {
        PostFile file = getAttachmentFileByFileId(fileId);
        File serverFile = new File(file.getFilepath() + "\\" + file.getFilename());
        boolean serverDeleteResult = serverFile.delete();
        int res = attachmentFileMapper.deleteAttachmentFileByFileId(fileId);
        return serverDeleteResult && res != 0;
    }

    public boolean deleteFileByPostId(Integer postId) {
        try {
            int rowsAffected = attachmentFileMapper.deleteByPostId(postId);
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
