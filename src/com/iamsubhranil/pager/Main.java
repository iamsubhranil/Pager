package com.iamsubhranil.pager;

import com.iamsubhranil.pager.algorithm.FirstInFirstOut;
import com.iamsubhranil.pager.algorithm.LeastFrequentlyUsed;
import com.iamsubhranil.pager.algorithm.LeastRecentlyUsed;
import com.iamsubhranil.pager.algorithm.OptimalReplacement;
import com.iamsubhranil.pager.algorithm.Replacer;
import com.iamsubhranil.pager.core.Memory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nNo of memory frames : ");

        Memory ram = new Memory(scanner.nextInt());
        ArrayList<Integer> references = new ArrayList<>();
        System.out.print("\nDo you want to enter manual references?\n1. Yes\n2. No : ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Enter the references sequentially in space separated manner, enter -1 when done");
            int firstref;
            while ((firstref = scanner.nextInt()) != -1)
                references.add(firstref);
        } else {
            System.out.print("\nEnter the number of references : ");
            int count = scanner.nextInt();
            System.out.println("Generating random sequence..");
            Random r = new Random();
            while (count-- > 0)
                references.add(r.nextInt(10000));
        }
        choice = 1;
        while (choice > 0 && choice < 5) {
            System.out.println("Select your algorithm of choice : ");
            System.out.println("1. Least Recently Used");
            System.out.println("2. First In First Out");
            System.out.println("3. Optimal Replacement");
            System.out.println("4. Least Frequently Used");
            System.out.println("5. Exit");
            System.out.print("\nYour choice : ");
            choice = scanner.nextInt();
            if (choice > 0 && choice < 5) {
                Replacer r = null;
                switch (choice) {
                    case 1:
                        r = new LeastRecentlyUsed();
                        break;
                    case 2:
                        r = new FirstInFirstOut();
                        break;
                    case 3:
                        r = new OptimalReplacement(references);
                        break;
                    case 4:
                        r = new LeastFrequentlyUsed();
                        break;
                }
                System.out.println("Placing references..");
                ram.flush();
                startReplace(r, references, ram);
                r.printStatistics();
            }
        }
    }

    private static void startReplace(Replacer algorithm, ArrayList<Integer> references, Memory ram) {
        references.forEach(reference -> {
            algorithm.insert(reference, ram);
        });
    }
}
