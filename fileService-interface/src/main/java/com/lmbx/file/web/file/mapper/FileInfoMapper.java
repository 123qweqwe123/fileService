package com.lmbx.file.web.file.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lmbx.file.web.file.domain.FileInfo;
import com.lmbx.file.web.file.domain.FileInfoExample;
import com.lmbx.file.web.file.filter.FileInfoFilter;
@Mapper
public interface FileInfoMapper {
    int countByExample(FileInfoExample example);

    int deleteByExample(FileInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    List<FileInfo> selectByExample(FileInfoExample example);

    FileInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FileInfo record, @Param("example") FileInfoExample example);

    int updateByExample(@Param("record") FileInfo record, @Param("example") FileInfoExample example);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);

    List<FileInfo> selectByFilter(FileInfoFilter fileInfoFilter);

}