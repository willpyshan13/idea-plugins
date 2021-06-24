package com.will.mvvmplug.utils;

import com.will.mvvmplug.Const;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.LinkedList;

public class FileUtil {
    public static String traverseFolder(String path) {
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            String file21 = getString(list, files);
            if (file21 != null) {
                return file21;
            }
            File tempFile;
            while (!list.isEmpty()) {
                tempFile = list.removeFirst();
                files = tempFile.listFiles();
                String file2 = getString(list, files);
                if (file2 != null) {
                    return file2;
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("没有发现文件");
        return "";
    }

    static public void mkDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 返回 找到的dir
     *
     * @param path
     * @param dir
     * @return
     */
    static public String findDir(String path, String dir) {
        if (path.contains(dir)) {
            String p = path.split(dir)[0];

            if (p.endsWith(Const.PATH_CHAR)) {
                return p + dir;
            }
            return p + Const.PATH_CHAR + dir;
        }

        if (path.endsWith(Const.PATH_CHAR)) {
            return path + dir;
        }
        return path + Const.PATH_CHAR + dir;
    }

    @Nullable
    public static String getString(LinkedList<File> list, File[] files) {
        for (File file2 : files) {
            if (file2.isDirectory()) {
                System.out.println("文件夹:" + file2.getAbsolutePath());
                if (file2.getName().endsWith("mvp")) {
                    return file2.getAbsolutePath();
                }
                list.add(file2);
            }
        }
        return null;
    }


    public static void writetoFile(String content, String filepath, String filename) {
        try {
            File floder = new File(filepath);
            // if file doesnt exists, then create it
            if (!floder.exists()) {
                floder.mkdirs();
            }
            File file = new File(filepath + "/" + filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            writeTxtFile(content, file.getPath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写文件 支持 utf-8
     *
     * @param str
     * @param file
     */
    private static void writeTxtFile(String str, String file) {
        BufferedWriter osw = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            osw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
            osw.write(str);
            osw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


    static public String readFile(String dir, String filename) {
        InputStream in = null;
        in = FileUtil.class.getResourceAsStream("../code/" + dir + "/" + filename);
        String content = "";

        try {
            //支持utf-8 不然汉子输出会出错
            InputStreamReader isr = new InputStreamReader(in, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            String fileContent = "";
            while ((line = br.readLine()) != null) {
                fileContent += line;
                // 补上换行符
                fileContent += "\r\n";
            }

            in.close();
            return fileContent;
        } catch (Exception e) {
        }

        return content;
    }

}
