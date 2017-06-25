package com.iamsubhranil.pager.algorithm;

import com.iamsubhranil.pager.core.Memory;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Subhranil Mukherjee on 6/25/2017.
 */
public class LeastFrequentlyUsed extends Replacer {

    private final SortedMap<Integer, Integer> map;

    public LeastFrequentlyUsed() {
        map = new TreeMap<>();
    }

    @Override
    String getName() {
        return "Least Frequently Used";
    }

    @Override
    public void insert(int pageNo, Memory ram) {
        print("Searching for page " + pageNo + "..");
        if (ram.containsFrame(pageNo)) {
            hit();
            map.replace(pageNo, map.get(pageNo) + 1);
            print("\tPage found in RAM..");
        } else {
            miss();
            if (ram.hasFreeFrame()) {
                ram.addFrame(pageNo);
                print("\tPage added to a free frame..");
            } else {
                print("\tReplacing with least frequently used page " + map.firstKey() + " with " + map.get(map.firstKey()) + " uses..");
                ram.replaceFrame(map.firstKey(), pageNo);
                map.remove(map.firstKey());
            }
            map.put(pageNo, 1);
        }
    }
}
