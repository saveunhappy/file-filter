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
     * @param extension 要过滤的文件扩展名，例如 .txt
     * @return 所有该文件夹（及其后代子文件夹中）匹配指定扩展名的文件的名字
     */

    // 第四步 匿名内部类
    public static List<String> filter(Path rootDirectory, String extension) throws IOException {
        List<String> names = new ArrayList<>();
        Files.walkFileTree(rootDirectory,new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                if(file.getFileName().toString().endsWith(extension)){
                    names.add(file.getFileName().toString());
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return names;
    }



    // 第三步 内部类
    /*

    public static List<String> filter(Path rootDirectory, String extension) throws IOException {
        FileFilterVisitor visitor = new FileFilterVisitor(extension);
        Files.walkFileTree(rootDirectory,visitor);
        return visitor.getFilterNames();
    }

    public static class FileFilterVisitor extends SimpleFileVisitor<Path>{
        private String extension;
        private List<String> filterNames = new ArrayList<>();

        public FileFilterVisitor(String extension) {
            this.extension = extension;
        }

        public List<String> getFilterNames() {
            return filterNames;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println(file);
            if(file.getFileName().toString().endsWith(extension)){
                filterNames.add(file.getFileName().toString());
            }
            return FileVisitResult.CONTINUE;
        }
    }
    */
}

// 第一步
/*
class MyFileVisitor implements FileVisitor {
    @Override
    public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
        System.out.println(dir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
        System.out.println(file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
*/


// 第二步 使用骨架实现
/*
class FileFilterVisitor extends SimpleFileVisitor<Path>{
    private String extension;
    private List<String> filterNames = new ArrayList<>();

    public FileFilterVisitor(String extension) {
        this.extension = extension;
    }

    public List<String> getFilterNames() {
        return filterNames;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file);
        if(file.getFileName().toString().endsWith(extension)){
            filterNames.add(file.getFileName().toString());
        }
        return FileVisitResult.CONTINUE;
    }
}
*/

