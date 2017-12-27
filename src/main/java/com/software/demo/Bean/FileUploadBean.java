package com.software.demo.Bean;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.IOException;
@Component
public class FileUploadBean {
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置文件大小限制 ,超出设置页面会抛出异常信息，
        // 这样在文件上传的地方就需要进行异常信息的处理了;
        factory.setMaxFileSize("256KB"); // KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("512KB");
        // Sets the directory location where files will be stored.

        factory.setLocation("E:\\software\\upload");
        return factory.createMultipartConfig();
    }
    public Boolean upload(MultipartFile file,String filename) {
        try {
            if (file.isEmpty()) {
                return false;
            }
            // 获取文件名
            //String fileName = file.getOriginalFilename();
            // System.out.println(fileName);
            // logger.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            //String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // System.out.println(suffixName);
            // logger.info("文件的后缀名为：" + suffixName);

            // 设置文件存储路径
            String filePath = "E://software//src//main//resources//static//upload//";
            String path = filePath +  filename;

            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            return true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
