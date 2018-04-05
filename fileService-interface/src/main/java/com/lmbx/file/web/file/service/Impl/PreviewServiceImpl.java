package com.lmbx.file.web.file.service.Impl;

import org.springframework.stereotype.Service;

import com.lmbx.file.web.file.service.PreviewService;
import com.lmbx.file.web.file.util.Office2PDF;

import java.io.*;
@Service
public class PreviewServiceImpl implements PreviewService {
    public byte[] getFile(byte[] fileByteArr, String name) {
        byte[] newByte = new byte[0];
        String s = System.getProperty("user.dir");
        String path = s + "/previewfile";
        try {
            File file = new File(path);
            //如果文件夹不存在则创建
            if (!file.exists() && !file.isDirectory()) {
                file.mkdir();
            }
            FileOutputStream fos = null;
            fos = new FileOutputStream(path + "/"+name);
            fos.write(fileByteArr);
            fos.close();
            File pdfFile = Office2PDF.openOfficeToPDF(path +"/"+ name);
            newByte = getBytes(pdfFile);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            deleteFile(path);
        }
        return newByte;
    }

    private byte[] getBytes(File file) throws IOException {
    	FileInputStream stream=new FileInputStream(file.getPath());
		int size=stream.available();
		byte data[]=new byte[size];
		stream.read(data);
		stream.close();
		stream=null;
		return data;
    }

    private static boolean deleteFile(String delpath) {
        try {
            File file = new File(delpath);
            // 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(delpath + "\\" + filelist[i]);
                    delfile.delete();
                }
            }
        } catch (Exception e) {
            System.out.println("deleteFile() Exception:" + e.getMessage());
        }
        return true;
    }
}
