package com.github.hcsp.polymorphism;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
     * @param extension     要过滤的文件扩展名，例如 .txt
     * @return 所有该文件夹（及其后代子文件夹中）匹配指定扩展名的文件的名字
     */
    public static List<String> filter(Path rootDirectory, String extension) throws IOException {
        File directory = new File(String.valueOf(rootDirectory));
        if (!directory.exists()) {
            return null;
        }
        List<String> fileNameList = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files == null) {
            return null;
        }
        for (File file : files) {
            String fileName = file.getName();
            if (file.isDirectory()) {
                fileNameList.addAll(Objects.requireNonNull(filter(file.toPath(), extension)));
            }
            if (fileName.endsWith(extension)) {
                fileNameList.add(fileName);
            }
        }
        return fileNameList;
    }

}
