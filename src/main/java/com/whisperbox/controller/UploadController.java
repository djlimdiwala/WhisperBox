package com.whisperbox.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Value("${whisperbox.upload-dir:uploads}")
    private String uploadDir;

    @PostMapping(
            value = "/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Map<String, String> uploadImage(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        Files.createDirectories(Paths.get(uploadDir));

        String extension =
                StringUtils.getFilenameExtension(
                        file.getOriginalFilename());

        String filename =
                UUID.randomUUID()
                        + "." + extension;

        Path destination =
                Paths.get(uploadDir)
                        .resolve(filename);

        Files.copy(
                file.getInputStream(),
                destination,
                StandardCopyOption.REPLACE_EXISTING);

        return Map.of(
                "url",
                "/uploads/" + filename
        );
    }

}