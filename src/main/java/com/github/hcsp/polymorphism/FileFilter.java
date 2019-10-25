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
        List<String> filteredFileNames = new ArrayList<>();
        FileVisitTools fileVisitTools = new FileVisitTools(filteredFileNames, extension);
        Files.walkFileTree(rootDirectory, fileVisitTools);
        return fileVisitTools.filteredFileNames();
    }

    static class FileVisitTools implements FileVisitor<Path> {
        private List<String> list;
        private String string;

        FileVisitTools(List<String> list, String str) {
            this.list = list;
            this.string = str;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
            String fileString = path.toAbsolutePath().toString();
            if (fileString.endsWith(string)) {
                list.add(path.getFileName().toString());
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path path, IOException e) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path path, IOException e) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        private List<String> filteredFileNames() {
            return this.list;
        }
    }
}
