package com.github.hcsp.polymorphism;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileFilter {
    public static void main(String[] args) throws IOException {
        Path projectDir = Paths.get(System.getProperty("user.dir"));
        Path testRootDir = projectDir.resolve("test-root");
        if (!testRootDir.toFile().isDirectory()) {
            throw new IllegalStateException(testRootDir.toAbsolutePath().toString() + "不存在！");
        }

        List<String> filteredFileNames = filter(testRootDir, ".csv");
        System.out.println(filteredFileNames);
    }

    /**
     * 实现一个按照扩展名过滤文件的功能
     *
     * @param rootDirectory 要过滤的文件夹
     * @param extension 要过滤的文件扩展名，例如 .txt
     * @return 所有该文件夹（及其后代子文件夹中）匹配指定扩展名的文件的名字
     */
    public static List<String> filter(Path rootDirectory, String extension) throws IOException {
        File file = new File(rootDirectory.toString());
        List<String> list = new ArrayList<>();
        add(list, extension, file);
        return list;
    }

    /**
     *
     * @param list 过滤得到的文件名 的 容器
     * @param extension 过滤拓展名
     * @param files 要过滤的文件
     */
    public static void add(List list, String extension, File... files) {

        for (File f:files) {
            File[] file = f.listFiles(new java.io.FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    String name = pathname.getName();
                    if (pathname.isFile() && name.endsWith(extension)) {
                        list.add(name);
                        return false;
                    }
                    if (pathname.isDirectory()) {
                        return true;
                    }
                    return false;
                }
            });
            add(list, extension, file);
        }
    }
}
