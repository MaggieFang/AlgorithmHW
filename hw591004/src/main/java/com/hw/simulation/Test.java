package com.hw.simulation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author by Maggie Fang. Email menogenfong@gmail.com. Date on 9/27/18
 * Talk is Cheap,Show me the Code.
 **/
public class Test {
    public static void main(String[] args) throws IOException {
        boolean firstTime = true;
        File file = new File("output.txt");
        if(file.exists()){
            firstTime = false;
        }else {
           file.createNewFile();
        }
        FileWriter writer = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter((writer));
        if(firstTime){
            bw.write(String.format("%-7s %-7s %-7s %-10s %s ","MC","CLA","CLS", "request","CLR"));
            bw.newLine();
        }else {
            int mc = 2, cla = 8,cls = 12,r = 1;
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(4);
            list.add(8);

            bw.write(String.format("%-7s %-7s %-7s %-10s %s",mc,cla,cls, r,list));
        }



        bw.flush();
        bw.close();
    }
}
