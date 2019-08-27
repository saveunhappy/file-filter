package com.github.hcsp.polymorphism;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyFileVisitor
 * @Description
 * @Author 25127
 * @Date 2019/8/27 16:56
 * @Email jie.wang13@hand-china.com
 **/
public class MyFileVisitor extends SimpleFileVisitor<Path> {

    /**
     * 要过滤文件的后缀名
     */
    private String extension;

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public MyFileVisitor(String extension) {
        this.extension = extension;
    }

    private List<String> filtedFileName = new ArrayList<>();

    public List<String> getFiltedFileName() {
        return filtedFileName;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if (file.getFileName().toString().endsWith(extension)){
            filtedFileName.add(file.getFileName().toString());
        }
        return super.visitFile(file, attrs);
    }
}
