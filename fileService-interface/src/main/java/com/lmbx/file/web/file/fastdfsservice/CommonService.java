package com.lmbx.file.web.file.fastdfsservice;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


public interface CommonService {


    String upload(MultipartFile file);

    byte[] download(String fileId);

    Map<String, Object> downloadWithMetaData(String fileId);

}
