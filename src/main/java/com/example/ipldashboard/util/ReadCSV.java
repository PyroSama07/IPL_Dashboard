package com.example.ipldashboard.util;

import com.example.ipldashboard.models.Match;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReadCSV {
    public List<Match> readCSV(String path) throws FileNotFoundException {
        List<Match> matches = new ArrayList<>();
        String[] data;
        try (CSVReader csvReader = new CSVReader(new FileReader(path))){
            String[] header =  csvReader.readNext();
            while((data = csvReader.readNext())!=null){
//                String[] data = line.split(",");
                Match match = new Match();

                for (int i=0;i<header.length;i++){
                    String fieldname = header[i];
                    String fielddata = data[i];

                    Field field = Match.class.getDeclaredField(fieldname);
                    field.setAccessible(true);

                    if (field.getType() == int.class){
                        field.setInt(match,Integer.parseInt(fielddata));
                    }
                    else if (field.getType() == LocalDate.class){
                        match.setDate(LocalDate.parse(fielddata));
                    }
                    else if (field.getType() == String[].class){
                        fielddata = fielddata.replace("'","");
                        fielddata = fielddata.substring(1,fielddata.length()-1);
                        field.set(match,fielddata.split(","));
                    }
                    else{
                        field.set(match,fielddata);
                    }
                }
                matches.add(match);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return matches;
    }

//    public static void main(String[] args) throws FileNotFoundException {
//        ReadCSV readCSV = new ReadCSV();
//        System.out.println(readCSV.readCSV("src/main/resources/IPL_Matches_2008_2022.csv"));
//    }
}
