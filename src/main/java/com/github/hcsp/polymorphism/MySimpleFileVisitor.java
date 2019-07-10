package com.github.hcsp.polymorphism;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class MySimpleFileVisitor extends SimpleFileVisitor<Path> {
    private List<String> filteredFileName = new ArrayList<>();
    private String extension;

    public MySimpleFileVisitor(String extension) {
        this.extension = extension;
    }

    public List<String> getFilteredFileName() {
        return filteredFileName;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if(file.getFileName().toString().endsWith(extension)) {
            filteredFileName.add(file.getFileName().toString());
        }
        return FileVisitResult.CONTINUE;
    }
}
