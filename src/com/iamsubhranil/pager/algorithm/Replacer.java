package com.iamsubhranil.pager.algorithm;

import com.iamsubhranil.pager.core.Memory;

/**
 * Created by Subhranil Mukherjee on 6/25/2017.
 */
public abstract class Replacer {

    private long hit = 0;
    private long miss = 0;
    private boolean verbose = false;

    public long getHit() {
        return hit;
    }

    public long getMiss() {
        return miss;
    }

    public double getHitRatio() {
        return (double) hit / (hit + miss);
    }

    public double getMissRatio() {
        return (double) miss / (hit + miss);
    }

    void hit() {
        hit++;
    }

    void miss() {
        miss++;
    }

    public void setVerbose(boolean isVerbose) {
        verbose = isVerbose;
    }

    void print(String toPrint) {
        if (verbose)
            System.out.println(toPrint);
    }

    abstract String getName();

    public void printStatistics() {
        System.out.println(getName() + " is in effect..");
        System.out.println("\tHits : " + hit + " Miss : " + miss);
        System.out.println("\tHit ratio : " + getHitRatio() + " Miss ratio : " + getMissRatio());
    }

    public abstract void insert(int pageNo, Memory ram);

}
