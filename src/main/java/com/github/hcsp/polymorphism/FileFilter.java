package com.github.hcsp.polymorphism;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wheelchen
 */
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
    public static List<String> filter(Path rootDirectory, String extension) {
        List<String> files = new ArrayList<>();

        try(DirectoryStream<Path> stream = Files.newDirectoryStream(rootDirectory)) {
            for (Path path: stream) {
                //如果是目录
                if (path.toFile().isDirectory()) {
                    files.addAll(filter(path, extension));
                }
                //如果是文件
                else {
                    String fileName = String.valueOf(path.getFileName());
                    if (fileName.endsWith(extension)) {
                        files.add(fileName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }
}
