package com.lmbx.file.web.file.filter;

import com.lmbx.file.core.mybatis.domain.PageFilter;

public class FileInfoFilter extends PageFilter {
    private String id;

    private String name;

    private String fileid;

    private String filesizemin;

    private String filesizemax;

    private String project;

    private String type;

    private String endTime;
    private String startTime;


    private String filetype;

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getFilesizemin() {
        return filesizemin;
    }

    public void setFilesizemin(String filesizemin) {
        this.filesizemin = filesizemin;
    }

    public String getFilesizemax() {
        return filesizemax;
    }

    public void setFilesizemax(String filesizemax) {
        this.filesizemax = filesizemax;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
