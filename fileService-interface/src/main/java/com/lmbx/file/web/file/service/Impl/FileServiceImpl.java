package com.lmbx.file.web.file.service.Impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lmbx.file.core.exception.ServiceException;
import com.lmbx.file.thirdservice.fdfs.impl.FastDFSFileServiceImpl;
import com.lmbx.file.web.file.domain.FileInfo;
import com.lmbx.file.web.file.filter.FileInfoFilter;
import com.lmbx.file.web.file.mapper.FileInfoMapper;
import com.lmbx.file.web.file.service.FileService;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileInfoMapper fileInfoMapper;

    @Autowired
    private FastDFSFileServiceImpl fastDfsfileService;
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    public String save(MultipartFile file, String project,String filetype) {
        String fileId;
        String fileName;
        String extName;
        String contentType;
        try {
            fileName = file.getOriginalFilename();
            extName = StringUtils.substringAfterLast(fileName, ".");
            NameValuePair[] pairs = new NameValuePair[1];
            pairs[0] = new NameValuePair("name", fileName);
            fileId  =  fastDfsfileService.uploadFile(file.getBytes(), extName, pairs);
            contentType = file.getContentType();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("上传失败，请联系管理员!");
        }
        try {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setUploadTime("");
            fileInfo.setProject(project);
            fileInfo.setFileid(fileId);
            fileInfo.setName(fileName);
            fileInfo.setFilesize(String.valueOf(file.getSize()));
            fileInfo.setType(extName);
            fileInfo.setContenttype(contentType);
            fileInfo.setFiletype(filetype);
            fileInfoMapper.insert(fileInfo);
            logger.info("保存文件信息成功，文件位置标识：{}", fileId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("文件信息保存失败，请联系管理员!");
        }
        return  fileId;
    }


    public List<FileInfo> query(FileInfoFilter fileInfoFilter) {
        List<FileInfo> lists = fileInfoMapper.selectByFilter(fileInfoFilter);
        return lists;
    }

}
