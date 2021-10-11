package com.catherine.dynamicconnectivity;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String PATH = "/src/com/catherine/dynamicconnectivity/";

    public static void main(String[] args) {
        try {
            String filePath = new File("").getAbsolutePath();
            FileReader fr = new FileReader(String.format("%s%stest.txt", filePath, PATH));
            Scanner stdIn = new Scanner(fr);
            int n = stdIn.nextInt();
            UF uf = new QuickFind(n);
            while (stdIn.hasNext()) {
                int p = stdIn.nextInt();
                int q = stdIn.nextInt();
                if (!uf.connected(p, q)) {
                    uf.union(p, q);
                    System.out.println(p + " " + q);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}