package com.example.davidgong.donation_tracker.model;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVFile {
    InputStream inputStream;

    /**
     * creates a new CSVFile
     *
     * @param inputStream the stream of data to read from the csv file
     */
    public CSVFile(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * returns a list of String[] which are the lines of inputStream split at ','
     *
     * @return a list of String[] which are the lines if inputStream split at ','
     */
    public List read() {
        List resultList = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                resultList.add(row);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: " + ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
        return resultList;
    }
}