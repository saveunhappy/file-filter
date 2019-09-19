package com.github.hcsp.polymorphism;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
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
     * @param extension     要过滤的文件扩展名，例如 .txt
     * @return 所有该文件夹（及其后代子文件夹中）匹配指定扩展名的文件的名字
     */
    public static List<String> filter(Path rootDirectory, String extension) throws IOException {
        List<String> filteredNames = new ArrayList<>();
//        fileFilterVisitor visitor = new fileFilterVisitor(extension);
        Files.walkFileTree(rootDirectory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (String.valueOf(file.getFileName()).endsWith(extension)) {
                    filteredNames.add(String.valueOf(file.getFileName()));
                }
                return FileVisitResult.CONTINUE;
            }
        });
//        return visitor.getFilteredNames();
        return filteredNames;
    }
//    private static class fileFilterVisitor extends SimpleFileVisitor<Path>{
//        private String extension;
//
//        public fileFilterVisitor(String extension) {
//            this.extension = extension;
//        }
//
//        private List<String> filteredNames = new ArrayList<>();
//
//        public List<String> getFilteredNames() {
//            return filteredNames;
//        }
//
//        @Override
//        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//            if(String.valueOf(file.getFileName()).endsWith(extension)){
//                filteredNames.add(String.valueOf(file.getFileName()));
//            }
//            return FileVisitResult.CONTINUE;
//        }
//    }
}
