package com.young.coal.business.controller;

import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadImageController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    String ACCESS_KEY = "Dv1hWgZr--j2d1boSh0uO7NaFigCFOxx-ESm_Nfn";
    String SECRET_KEY = "GRLfsQM4DxgfPvRPI4vANXYvCURjSgJBW9MnLy1k";

    //要上传的空间
    String bucketName = "coalapp";

    String uploadDir = ClassUtils.getDefaultClassLoader().getResource("").getPath();

    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    Configuration cfg = new Configuration(Zone.zone2());
    UploadManager uploadManager = new UploadManager(cfg);

    @RequestMapping("/uploadImage")
    public String upload(@RequestParam MultipartFile file) throws IOException {

        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String fileName = UUID.randomUUID() + "." + suffix;

        String token = "";
        if (StringUtils.isEmpty(fileName)) {
            token = auth.uploadToken(bucketName);
        } else {
            token = auth.uploadToken(bucketName, fileName);
        }

        Response response = uploadManager.put(file.getInputStream(), fileName, token, null, null);

        return "http://or0qspriu.bkt.clouddn.com/" + fileName;
    }

}
