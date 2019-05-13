package com.ddong.upload.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("upload")
public class UploadController {
 @Autowired
 private FastFileStorageClient fastClient;
    private List<String> list =  Arrays.asList("image/jpg","image/jpeg");
    @PostMapping("image")
    public ResponseEntity uploadImage(MultipartFile file){
        //获取图片后缀
        String contentType = file.getContentType();
        try {
        //判断后缀是否为图片格式
        if (!list.contains(contentType)){
            System.out.println("上传失败"+contentType);
            return ResponseEntity.badRequest().build();
        }
        //获取文件的内容
            BufferedImage read = ImageIO.read(file.getInputStream());

            if (read==null){
                System.out.println("图片内容错误，"+read);
                return ResponseEntity.badRequest().build();
            }
            //什么都没问题才上传
            //获取后缀
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            //获取连接
            StorePath storePath = fastClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);
            String fullPath = storePath.getFullPath();
            String originalFilename = file.getOriginalFilename();
            System.out.println(fullPath);
            return ResponseEntity.ok("http://image.ddong.com/"+fullPath);
        } catch (IOException e) {
            e.printStackTrace();
        }return ResponseEntity.badRequest().build();
    }
}
