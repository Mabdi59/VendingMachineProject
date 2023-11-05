package com.techelevator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class History {
    private List<String> logs = new ArrayList<>();
    private Map<String, Integer> salesReport = new TreeMap<>();

    public static String currentTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

//    private void init(){
//
//    }
//    private class SalesReport{
//
//    }
//    private class Logs{
//
//    }
}
