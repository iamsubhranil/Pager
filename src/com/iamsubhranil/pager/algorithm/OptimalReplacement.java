package com.iamsubhranil.pager.algorithm;

import com.iamsubhranil.pager.core.Memory;

import java.util.ArrayList;

/**
 * Created by Subhranil Mukherjee on 6/25/2017.
 */
public class OptimalReplacement extends Replacer {

    private final ArrayList<Integer> references;
    private int count = 0;

    public OptimalReplacement(ArrayList<Integer> ref) {
        references = ref;
    }

    @Override
    String getName() {
        return "Optimal Replacement";
    }

    @Override
    public void insert(int pageNo, Memory ram) {
        if (ram.containsFrame(pageNo)) {
            hit();
        } else {
            miss();
            if (ram.hasFreeFrame()) {
                ram.addFrame(pageNo);
            } else {
                int maxDistance = 0;
                int valueToReplace = 0;
                int start = 0;
                while (start < ram.getFrameCount()) {
                    int item = ram.getPageAt(start);
                    int i = count;
                    int distance = 0;
                    while (i < references.size()) {
                        if (references.get(i) == item)
                            break;
                        i++;
                        distance++;
                    }
                    if (distance > maxDistance) {
                        maxDistance = distance;
                        valueToReplace = item;
                    }
                    start++;
                }
                ram.replaceFrame(valueToReplace, pageNo);
            }
        }
        count++;
    }
}
