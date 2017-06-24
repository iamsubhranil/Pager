package com.iamsubhranil.pager.algorithm;

import com.iamsubhranil.pager.core.Memory;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Subhranil Mukherjee on 6/25/2017.
 */
public class FirstInFirstOut extends Replacer {

    private final Queue<Integer> references;

    public FirstInFirstOut() {
        references = new ArrayDeque<>();
    }

    @Override
    String getName() {
        return "First In First Out";
    }

    @Override
    public void insert(int pageNo, Memory ram) {
        print("Searching for page " + pageNo + "..");
        if (ram.containsFrame(pageNo)) {
            hit();
            print("\tPage found in RAM..");
        } else {
            miss();
            print("\tPage not found in RAM..");
            if (ram.hasFreeFrame()) {
                ram.addFrame(pageNo);
                print("\tInserted in a free frame..");
            } else {
                print("\tReplacing oldest page " + references.peek() + "..");
                ram.replaceFrame(references.poll(), pageNo);
            }
            references.add(pageNo);
        }
    }
}
