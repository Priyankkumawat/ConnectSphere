package com.social.ConnectSphere.Service.ServiceImpl;


import com.social.ConnectSphere.Service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //file name from file
        String filename = file.getOriginalFilename();

        // modify filename as there maybe some filename with same name
        String randomId = UUID.randomUUID().toString();
        String filename1 = randomId.concat(filename.substring(filename.lastIndexOf(".")));
        // full filepath of file
        String filePath = path + File.separator + filename1;

        // make filepath folder if not exists
        File folder = new File(path);
        if(!folder.exists()){
            folder.mkdir();
        }
        System.out.println(filePath);

        // copy the file and paste it to filepath
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return filename1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }
}
