package com.will.mvvmplug.filesize;

import java.io.FileNotFoundException;

public class FileTest {

    static String PATH = "D:\\work\\vv_official\\vvlife2\\XMVVRIDER";

    public static void main(String[] args) throws FileNotFoundException {
        // TODO 自动生成的方法存根

        FileLogic fileLogic = new FileLogic(1000);
        fileLogic.printFile(PATH, "XMVVRIDER");


    }
}
