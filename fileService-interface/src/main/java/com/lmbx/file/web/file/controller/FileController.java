package com.lmbx.file.web.file.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.csource.common.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.util.StringUtil;
import com.lmbx.file.web.file.domain.FileInfo;
import com.lmbx.file.web.file.fastdfsservice.CommonService;
import com.lmbx.file.web.file.filter.FileInfoFilter;
import com.lmbx.file.web.file.service.FileService;
import com.lmbx.file.web.file.service.PreviewService;

/**
* @author qianyanan
* @date 2018年4月2日 
* Description:
*/
@RestController
@RequestMapping("/common/file")
public class FileController {

    @Autowired
    FileService fileService;
    @Autowired
    private CommonService commonService;
    @Autowired
    PreviewService previewService;

    @GetMapping("/preview")
    public ResponseEntity<Resource> showImage(String fileid, String type, String name) throws IOException {
        byte[] fileByteArr = new byte[0];
        if (fileid.contains("doc") || fileid.contains("docx") ||
                fileid.contains("xls") || fileid.contains("xlsx") || fileid.contains("ppt")
                || fileid.contains("pptx")) {
            fileByteArr = previewService.getFile(commonService.download(fileid), name);
        } else {
            fileByteArr = commonService.download(fileid);
        }
        ByteArrayResource resource = new ByteArrayResource(fileByteArr);
        return ResponseEntity.ok()
                .contentType(getContentType(fileid))
                .contentLength(fileByteArr.length)
                .body(resource);
    }

    private MediaType getContentType(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (suffix.contains("bmp") || suffix.contains("jpg")
                || suffix.contains("png") || suffix.contains("gif")) {
            return MediaType.IMAGE_JPEG;
        } else if (suffix.contains("txt")) {
            return MediaType.TEXT_PLAIN;
        } else {
            return MediaType.APPLICATION_PDF;
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> save(@RequestParam(value="file",required=true) MultipartFile file
    		, @RequestParam(value ="project",required=true) String project, @RequestParam(value ="filetype",required=true) String filetype) {
        if(StringUtil.isEmpty(filetype)||StringUtil.isEmpty(project)) {
        	return ResponseEntity.status(500).body("参数项目类型和文件类型不能为空");
        }
    	return ResponseEntity.status(200).body(fileService.save(file, project,filetype));
    }

    @GetMapping("/query")
    public List<FileInfo> find(FileInfoFilter fileInfoFilter) {
        return fileService.query(fileInfoFilter);
    }
    @GetMapping("/download2")
    public void upload2(String fileid) throws UnsupportedEncodingException {
    	System.out.println("sssssss");
    	Map<String, Object> fileWithMetaData = commonService.downloadWithMetaData(fileid);
        
        byte[] fileByteArr = (byte[]) fileWithMetaData.get("body");
        ByteArrayResource resource = new ByteArrayResource(fileByteArr);
        NameValuePair[] metaData = (NameValuePair[]) fileWithMetaData.get("meta");
        String fileName = "";
        if (metaData != null) {
            for (NameValuePair pair : metaData) {
                if ("name".equals(pair.getName())) {
                    fileName = pair.getValue();
                    break;
                }
            }
        }
    }
    
    @GetMapping("/download3")
    public void upload3(String fileid) throws UnsupportedEncodingException {
    	System.out.println("333");
    	Map<String, Object> fileWithMetaData = commonService.downloadWithMetaData(fileid);
        
        byte[] fileByteArr = (byte[]) fileWithMetaData.get("body");
        ByteArrayResource resource = new ByteArrayResource(fileByteArr);
        NameValuePair[] metaData = (NameValuePair[]) fileWithMetaData.get("meta");
        String fileName = "";
        if (metaData != null) {
            for (NameValuePair pair : metaData) {
                if ("name".equals(pair.getName())) {
                    fileName = pair.getValue();
                    break;
                }
            }
        }
    }
    @GetMapping("/download4")
    public void upload4(String fileid) throws UnsupportedEncodingException {
    	System.out.println("4444");
    	Map<String, Object> fileWithMetaData = commonService.downloadWithMetaData(fileid);
        
        byte[] fileByteArr = (byte[]) fileWithMetaData.get("body");
        ByteArrayResource resource = new ByteArrayResource(fileByteArr);
        NameValuePair[] metaData = (NameValuePair[]) fileWithMetaData.get("meta");
        String fileName = "";
        if (metaData != null) {
            for (NameValuePair pair : metaData) {
                if ("name".equals(pair.getName())) {
                    fileName = pair.getValue();
                    break;
                }
            }
        }
    }
    @GetMapping("/download")
    public ResponseEntity<Resource> upload(String fileid) throws UnsupportedEncodingException {
    	System.out.println("sssssss");
    	Map<String, Object> fileWithMetaData = commonService.downloadWithMetaData(fileid);
        
        byte[] fileByteArr = (byte[]) fileWithMetaData.get("body");
        ByteArrayResource resource = new ByteArrayResource(fileByteArr);
        NameValuePair[] metaData = (NameValuePair[]) fileWithMetaData.get("meta");
        String fileName = "";
        if (metaData != null) {
            for (NameValuePair pair : metaData) {
                if ("name".equals(pair.getName())) {
                    fileName = pair.getValue();
                    break;
                }
            }
        }
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") )
                .header("fileName", new String(fileName.getBytes("UTF-8"), "ISO8859-1")).contentType(MediaType.parseMediaType("application/octet-stream"))
                .contentLength(fileByteArr.length)
                .body(resource);
    }
}

