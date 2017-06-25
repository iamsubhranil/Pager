package com.iamsubhranil.pager;

import com.iamsubhranil.pager.algorithm.*;
import com.iamsubhranil.pager.core.Memory;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Memory ram = new Memory(1000);
        ArrayList<Integer> references = new ArrayList<>();
        //   references.addAll(Arrays.asList(0,1,4,2,0,4,3,5,1,6,3,2,3,2,6,2,1,3,4,2,1,0));
        int count = 5000;
        Random r = new Random();
        while (count-- > 0)
            references.add(r.nextInt(10000));

        LeastRecentlyUsed lru = new LeastRecentlyUsed();
        startReplace(lru, references, ram);
        lru.printStatistics();

        ram.flush();
        FirstInFirstOut fifo = new FirstInFirstOut();
        startReplace(fifo, references, ram);
        fifo.printStatistics();

        ram.flush();
        OptimalReplacement or = new OptimalReplacement(references);
        startReplace(or, references, ram);
        or.printStatistics();

        ram.flush();
        LeastFrequentlyUsed lfu = new LeastFrequentlyUsed();
        startReplace(lfu, references, ram);
        lfu.printStatistics();
    }

    private static void startReplace(Replacer algorithm, ArrayList<Integer> references, Memory ram) {
        references.forEach(reference -> {
            algorithm.insert(reference, ram);
        });
    }
}
