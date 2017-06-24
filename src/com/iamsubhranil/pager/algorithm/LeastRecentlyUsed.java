package com.iamsubhranil.pager.algorithm;

import com.iamsubhranil.pager.core.Memory;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Subhranil Mukherjee on 6/25/2017.
 */
public class LeastRecentlyUsed extends Replacer {

    private final Deque<Integer> references;

    public LeastRecentlyUsed() {
        references = new ArrayDeque<>();
    }

    @Override
    String getName() {
        return "Least Recently Used";
    }

    @Override
    public void insert(int pageNo, Memory ram) {
        print("Searching for page " + pageNo + "..");
        if (ram.containsFrame(pageNo)) {
            hit();
            print("\tPage found in RAM..");
            references.remove(pageNo);
        } else {
            miss();
            print("\tPage not found in RAM..");
            if (ram.hasFreeFrame()) {
                ram.addFrame(pageNo);
                print("\tInserted in a free frame..");
            } else {
                print("\tReplacing least recently used page " + references.peekLast() + "..");
                ram.replaceFrame(references.pollLast(), pageNo);
            }
        }
        references.push(pageNo);
    }
}
