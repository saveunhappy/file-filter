package com.github.hcsp.polymorphism;


import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileFilterVisitor extends SimpleFileVisitor<Path> {
    private String extension;
    private List<String> Filterlist = new ArrayList<String>();

    public FileFilterVisitor(String extension) {
        this.extension = extension;
    }

    public List<String> getFilterlist() {
        return Filterlist;
    }


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {
        if (file.getFileName().toString().endsWith(extension)) {
            Filterlist.add(file.getFileName().toString());
        }
        return FileVisitResult.CONTINUE;
    }

}
