package com.github.hcsp.polymorphism;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> fileNames = new ArrayList<String>();
        GetAllFileNames(rootDirectory.toString(), fileNames);
        for (String file : fileNames) {
            if (file.endsWith(extension)) {
                result.add(file);
            }
        }

        return result;
    }

    private static void GetAllFileNames(String rootDirectory, ArrayList<String> result) {

        File folder = new File(rootDirectory);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                result.add(listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                GetAllFileNames(listOfFiles[i].getPath(), result);
            }
        }


    }
}
