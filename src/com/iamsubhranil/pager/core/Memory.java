package com.iamsubhranil.pager.core;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Subhranil Mukherjee on 6/25/2017.
 */
public class Memory {

    private final ArrayList<Integer> frames;
    private final int frameCount;

    public Memory(int frameCount) {
        frames = new ArrayList<>(frameCount);
        this.frameCount = frameCount;
    }

    public boolean containsFrame(int pageNo) {
        return frames.contains(pageNo);
    }

    public void replaceFrame(int oldPage, int newPage) {
        frames.set(frames.indexOf(oldPage), newPage);
    }

    public int getFrameCount() {
        return frameCount;
    }

    public boolean hasFreeFrame() {
        return frames.size() < frameCount;
    }

    public void addFrame(int newPage) {
        frames.add(newPage);
    }

    public void flush() {
        frames.clear();
    }

    public int getPageAt(int index) {
        return frames.get(index);
    }

}
