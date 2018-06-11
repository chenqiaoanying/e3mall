package cn.e3mall.controller;

import cn.e3mall.common.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PictureController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public Map uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile) {
        String fileName = uploadFile.getOriginalFilename();
        String extName = fileName.substring(fileName.lastIndexOf('.') + 1);
        try {
            FastDFSClient client = new FastDFSClient("classpath:conf/client.conf");
            String url = client.uploadFile(uploadFile.getBytes(), extName);
            Map<String, Object> result = new HashMap<>();
            result.put("error", 0);
            result.put("url", IMAGE_SERVER_URL + url);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> result = new HashMap<>();
            result.put("error", 1);
            result.put("message", "图片上传失败");
            return result;
        }

    }

}
