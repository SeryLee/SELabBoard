package com.example.selabboard.service.Impl;

import com.example.selabboard.model.entity.File;
import com.example.selabboard.repository.FileRepository;
import com.example.selabboard.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {
    @Value("${file.dir}")
    private String fileDir;

    private final FileRepository fileRepository;

    public Long saveFile(MultipartFile files) throws IOException {
        if(files.isEmpty()) {
            return null;
        }

        String originalName = files.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String extension = originalName.substring(originalName.lastIndexOf("."));
        String savedName = uuid + extension;
        String savedPath = fileDir + savedName;

        File file = File.builder()
                .originalName(originalName)
                .savedName(savedName)
                .savedPath(savedPath)
                .build();

        files.transferTo(new java.io.File(savedPath));
        File savedFile = fileRepository.save(file);

        return savedFile.getId();
    }

    @Override
    public File getFile(Long fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(null);
    }
}
