package com.github.hcsp.polymorphism;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
        List<String> filterFiles = new ArrayList<>();
        Queue<File> fileQueue = new LinkedList<>();
        File rootDir = new File(rootDirectory.toUri());
        fileQueue.add(rootDir);
        while (!fileQueue.isEmpty()) {
            File file = fileQueue.poll();
            for (File f : Objects.requireNonNull(file.listFiles())) {
                if (f.isDirectory()) {
                    fileQueue.add(f);
                } else if (f.getName().endsWith(extension)) {
                    filterFiles.add(f.getName());
                }
            }
        }
        return filterFiles;
    }
}
