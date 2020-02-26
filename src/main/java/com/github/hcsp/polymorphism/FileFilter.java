package com.github.hcsp.polymorphism;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
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

    // public static class FileVisitorFilter extends SimpleFileVisitor<Path> {
    //     private String extension;
    //     private List<String> fileList = new ArrayList<>();
    //
    //     public FileVisitorFilter(String extension) {
    //         this.extension = extension;
    //     }
    //
    //     @Override
    //     public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    //         if (file.getFileName().toString().endsWith(extension)) {
    //             fileList.add(file.getFileName().toString());
    //         }
    //         return FileVisitResult.CONTINUE;
    //     }
    //
    //     public List<String> getFileList() {
    //         return fileList;
    //     }
    // }

    /**
     * 实现一个按照扩展名过滤文件的功能
     *
     * @param rootDirectory 要过滤的文件夹
     * @param extension     要过滤的文件扩展名，例如 .txt
     * @return 所有该文件夹（及其后代子文件夹中）匹配指定扩展名的文件的名字
     */
    public static List<String> filter(Path rootDirectory, String extension) throws IOException {
        // FileVisitorFilter visitorFilter = new FileVisitorFilter(extension);
        List<String> fileList = new ArrayList<>();
        Files.walkFileTree(rootDirectory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.getFileName().toString().endsWith(extension)) {
                    fileList.add(file.getFileName().toString());
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return fileList;
    }
}
