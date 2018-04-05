package com.lmbx.file.web.file.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lmbx.file.web.file.domain.FileInfo;
import com.lmbx.file.web.file.filter.FileInfoFilter;

/**
* @author qianyanan
* @date 2018年4月2日 
* Description:
*/
public interface FileService {
	public String save(MultipartFile file, String project,String filetype);
	public List<FileInfo> query(FileInfoFilter fileInfoFilter);
}
