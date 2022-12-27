package com.example.selabboard.service;

import com.example.selabboard.model.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    Long saveFile(MultipartFile file) throws IOException;

    File getFile(Long fileId);
}
