package com.lmbx.file.web.file.fastdfsservice.Impl;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lmbx.file.core.exception.ServiceException;
import com.lmbx.file.thirdservice.fdfs.impl.FastDFSFileServiceImpl;
import com.lmbx.file.web.file.fastdfsservice.CommonService;


/**
* @author qianyanan
* @date 2018年4月2日 
* Description:
*/
@Service
public class CommonServiceImpl implements CommonService {

    Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);


    @Autowired
    private FastDFSFileServiceImpl fileService;
    /**
     * 下拉框限制条数
     */
    private static final int COMBOX_LIMIT = 15;
    /**
     * 自动补全对应 mybatis statement
     */
    private static final String autoCompleteStatement = "com.lmbx.csp.web.common.mapper.AutoCompleteMapper.selectComboxData";

    @Override
    public String upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String extName = StringUtils.substringAfterLast(fileName, ".");
        try {
            NameValuePair[] pairs = new NameValuePair[1];
            pairs[0] = new NameValuePair("name", fileName);
            return fileService.uploadFile(file.getBytes(), extName, pairs);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件上传失败，文件名：{}", fileName);
            throw new ServiceException("上传失败，请联系管理员!");
        }
    }

    @Override
    public byte[] download(String fileId) {
        return fileService.downloadFile(fileId);
    }

    @Override
    public Map<String, Object> downloadWithMetaData(String fileId) {
        return fileService.downloadFileWithMetaData(fileId);
    }
}
