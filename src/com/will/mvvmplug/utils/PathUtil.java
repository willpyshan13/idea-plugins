package com.will.mvvmplug.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class PathUtil {

   static public String findAndMkDir(String rootPath, String dir) {
        String path = FileUtil.findDir(rootPath, dir);
        File file = new File(path);
        try {
            FileUtils.forceMkdir(file);
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return path;
    }
}
