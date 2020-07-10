package com.csc3020.lecture19.go5621;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lecture19Source {

    public static void main(String[] args) {
        try {
            readData();
            System.out.println("-----------------------------------");

            readThemAll();
            System.out.println("-----------------------------------");

        } catch (IOException e) {
            e.printStackTrace();
        } catch(SecurityException e) {
            e.printStackTrace();
        }

        try {
            FileSystem zipFs = openZip(Paths.get("myData.zip"));
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        String[] data = {
                "Line 1",
                "Line 2 2",
                "Line 3 3 3",
                "Line 4 4 4 4",
                "Line 5 5 5 5 5",
        };

        try(FileSystem zipFs = openZip(Paths.get("myData.zip"))) {
            System.out.println("Before copying");
            copyToZip(zipFs);
            System.out.println("After copying");

            System.out.println("Before writing V1");
            writeToFileInZipV1(zipFs, data);
            System.out.println("After Writing V1");

            System.out.println("Before writing V2");
            writeToFileInZipV2(zipFs, data);
            System.out.println("After Writing V2");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }

    }

    private static void readData() throws IOException {
        try(BufferedReader br = Files.newBufferedReader(Paths.get("data.txt"))) {
            String inputValue;
            while((inputValue = br.readLine()) != null) {
                System.out.println(inputValue);
            }
        }
    }

    private static void readThemAll() throws IOException, SecurityException {
        List<String> lines = Files.readAllLines(Paths.get("data.txt"));
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private static FileSystem openZip(Path zipPath) throws IOException, URISyntaxException {
        Map<String, String> providerProps = new HashMap<>();
        providerProps.put("Create", "true");
        URI zipUri = new URI("jar:file", zipPath.toUri().getPath(), null);

        FileSystem zipFs = FileSystems.newFileSystem(zipUri, providerProps);
        return zipFs;
    }

    private static void copyToZip(FileSystem zipFs) throws IOException {
        Path sourceFile = Paths.get("file1.txt");
        //Path sourceFile = FileSystems.getDefault().getPath("file1.txt");
        Path destinationFile = zipFs.getPath("/file1Copy.txt");
        Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);
    }

    private static void writeToFileInZipV1(FileSystem zipFs, String[] data) throws IOException {
        try(BufferedWriter bw = Files.newBufferedWriter(zipFs.getPath("/newFile1.txt"))) {
            for (String dataItem: data) {
                bw.write(dataItem);
                bw.newLine();
            }
        }
    }

    private static void writeToFileInZipV2(FileSystem zipFs, String[] data) throws IOException {
        Files.write(zipFs.getPath("/newFile2.txt"), Arrays.asList(data), Charset.defaultCharset(), StandardOpenOption.CREATE);
    }

}
