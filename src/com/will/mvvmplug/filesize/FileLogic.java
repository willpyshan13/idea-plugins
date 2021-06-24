package com.will.mvvmplug.filesize;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;

public class FileLogic {

    /**
     * 阈值
     */
    int maxLength = 1000;

    FilterData filterData = new FilterData();
    String projectPath = "";

    public FileLogic(int size) {
        maxLength = size;
    }

    public FilterData printFile(String path, String projectName) {
        projectPath = path;
        //过滤大的文件Builder
        StringBuilder stringBuilder = new StringBuilder();
        filterData.fileMap.clear();
        long startTime = System.currentTimeMillis();
        System.out.println("start = loading... ");
        stringBuilder.append("start\n");

        //获取其file对象
        File file = new File(path);
        try {
            logic(file);
        } catch (Exception e) {

        }

        sort(filterData.fileMap.entrySet()).forEach(map -> {
            try {
                String tmpPath = map.getKey();
                if (tmpPath.contains(path)) {
                    tmpPath = tmpPath.substring(path.length());
                }
                if (tmpPath.contains(projectName)) {
                    tmpPath = tmpPath.substring(tmpPath.indexOf(projectName) + projectName.length());
                }

                System.out.println(map.getValue() / 1000 + "k  " + tmpPath);
                String target = map.getValue() / 1000 + "k  " + tmpPath + "\n";
                stringBuilder.append(target);
            } catch (Exception e) {
                System.out.println("error" + e.toString());
            }
        });

        System.out.println("end = loading " + (System.currentTimeMillis() - startTime));
        stringBuilder.append("end " + (System.currentTimeMillis() - startTime) + "\n");
        filterData.fileStrBuilder = stringBuilder;


        //相同的Builder
        filterData.sameStrBuilder = new StringBuilder();
        filterData.sameStrBuilder.append("≈  ---start\n");
        filterData.sameMap.forEach(new BiConsumer<Float, List<String>>() {
            @Override
            public void accept(Float len, List<String> list) {
                if (list != null || list.size() > 1) {
                    judgeSameFile(projectName, len, list);
                }
            }
        });
        filterData.sameStrBuilder.append("≈  ---end \n");

        return filterData;
    }

    public void judgeSameFile(String projectName, Float len, List<String> list) {
        if (list != null || list.size() > 1) {
            HashMap<String, ArrayList<String>> map = new HashMap<>();
            for (String item : list) {
                try {
                    String md5 = DigestUtils.md5Hex(FileUtils.openInputStream(new File(item)));
                    ArrayList<String> cacheList = map.get(md5);
                    if (cacheList == null) {
                        cacheList = new ArrayList<>();
                        map.put(md5, cacheList);
                    }
                    cacheList.add(item);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            map.forEach(new BiConsumer<String, ArrayList<String>>() {
                @Override
                public void accept(String s, ArrayList<String> tmpList) {
                    //真正相同的文件
                    if (tmpList != null && tmpList.size() > 1) {
                        tmpList.forEach(item -> {
                            String targetItem = item;
                            if (item.contains(projectName)) {
                                targetItem = item.substring(item.indexOf(projectName) + projectName.length());
                            }
                            filterData.sameStrBuilder.append("≈ " + targetItem + "\n");
                        });
                        filterData.sameStrBuilder.append("\n");
                    }
                }
            });
        }
    }

    public HashMap<String, Long> filter() {
        HashMap<String, Long> tmpFileMap = new HashMap<>();
        filterData.fileMap.forEach((tmpPath, tmpLength) -> {
            float size = tmpLength / 1000F;
            if (!tmpPath.contains("build") && tmpPath.contains("res") && size > 100) {
                tmpFileMap.put(tmpPath, tmpLength);
            }
        });

        return tmpFileMap;
    }

    public List<Map.Entry<String, Long>> sort(Collection<Map.Entry<String, Long>> c) {
        List<Map.Entry<String, Long>> list = new ArrayList<>(c);
        Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {

            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        return list;
    }

    public void logic(File a) throws FileNotFoundException {
        String path = a.getPath();
        //获取其file对象
        File file = new File(path);
        //遍历path下的文件和目录，放在File数组中
        File[] fs = file.listFiles();
        for (File f : fs) {
            String fPath = f.getPath();

            //若非目录(即文件)，对其进行遍历
            if (!f.isDirectory()) {

                if (!fPath.contains("build") && !fPath.contains("\\out\\") && fPath.contains("res") && !fPath.contains(".li")) {
                    float size = f.length();
                    if (size > maxLength) {
                        filterData.fileMap.put(fPath, f.length());
                    }
                    List<String> cacheList = filterData.sameMap.get(size);
                    if (cacheList == null) {
                        cacheList = new ArrayList<>();
                        filterData.sameMap.put(size, cacheList);
                    }
                    cacheList.add(fPath);

                }
            } else if (!fPath.contains(".gradle") && !fPath.contains("build")) {
                logic(f);
            }
        }
    }

    public static class FilterData {
        /**
         * 放文件路径和长度
         */
        private HashMap<String, Long> fileMap = new HashMap<>();

        /**
         * 放文件长度，和相同长度的列表
         */
        private HashMap<Float, List<String>> sameMap = new HashMap<>();


        /**
         * 过滤大文件
         */
        public StringBuilder fileStrBuilder = new StringBuilder();

        /**
         * 相似文件
         */
        public StringBuilder sameStrBuilder = new StringBuilder();
    }
}



