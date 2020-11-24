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
    public static List<String> filter(Path rootDirectory, String extension) throws IOException {
            List<String> names = new ArrayList<>( );

            //walkFileTree 方法对文件夹进行遍历IO流操作
            Files.walkFileTree( rootDirectory, new SimpleFileVisitor<Path>() {
                //visitFile 方法获取路径中的文件夹 并遍历查找
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    //把文件后缀相同的文件路径放到集合中
                    if(file.getFileName().toString().endsWith( extension )) {
                        names.add(file.getFileName().toString());
                    }
                    return FileVisitResult.CONTINUE;
                }

            } );

            return names;
    }
}
