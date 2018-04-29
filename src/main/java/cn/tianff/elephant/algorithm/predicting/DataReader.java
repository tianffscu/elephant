package cn.tianff.elephant.algorithm.predicting;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DataReader {

    public static void main(String[] args) throws IOException{

        Path path = FileSystems.getDefault().getPath("day.txt");
        List<String> contents = Files.readAllLines(path);

        ATPDC atpdc = new ATPDC();

    }

}
