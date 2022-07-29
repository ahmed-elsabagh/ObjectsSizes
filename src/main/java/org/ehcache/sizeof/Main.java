package org.ehcache.sizeof;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        SizeOf sizeOf = SizeOf.newInstance(); // (1)
        long shallowSize = sizeOf.sizeOf("someObjectsomeObjectsomeObjectsomeObjectsomeObjectsomeObjectsomeObjectsomeObjectsomeObjectsomeObjectsomeObjectsomeObject"); // (2)
        long deepSize = sizeOf.deepSizeOf("someObjectsomeObjectsomeObjectsomeObjectsomeObjectsomeObjectsomeObjectsomeObjectsomeObjectsomeObjectsomeObjectsomeObject"); // (3)

        Thread.currentThread().setName("Testing memory");

        String fileName = "C:\\Users\\ahmad_elsabagh\\Desktop\\data.txt";

        //read file into stream, try-with-resources
        AtomicLong consumedMemory = new AtomicLong();
        List<String> list = new ArrayList<>();

        new Scanner(System.in).nextLine();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(line->{
                consumedMemory.addAndGet(sizeOf.deepSizeOf(line));
//                list.add(line);
            });
            System.out.println("Memory consumption in bytes = " + consumedMemory + " = " + consumedMemory.get()/1024/1024 + " MG");
            System.out.println("Array size in bytes = " + sizeOf.deepSizeOf(list) );

        } catch (IOException e) {
            e.printStackTrace();
        }
        new Scanner(System.in).nextLine();
    }
}
