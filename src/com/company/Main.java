package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static int size;
    static int[][] field;

    static boolean isAiliveDot(int x, int y) {
        return (field[y][x] == 1 || field[y][x] == 3);
    }

    static void updateField() {
        System.out.println("-------------------------------------------");

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int count = 0;

                if (i > 0 && isAiliveDot(j, i-1)) count++;
                if (i < size-1 && isAiliveDot(j, i+1)) count++;
                if (j > 0 && isAiliveDot(j-1, i)) count++;
                if (j < size-1 && isAiliveDot(j+1, i)) count++;

                if (i > 0 && j > 0 && isAiliveDot(j-1, i-1)) count++;
                if (i > 0 && j < size-1 && isAiliveDot(j+1, i-1)) count++;
                if (i < size-1 && j > 0 && isAiliveDot(j-1, i+1)) count++;
                if (i < size-1 && j < size-1 && isAiliveDot(j+1, i+1)) count++;

                if ((count == 2 || count == 3) && field[i][j] == 1) field[i][j] = 3;
                else if (count == 3) field[i][j] = 2;

                if (isAiliveDot(j, i)) System.out.print("#");
                else System.out.print(" ");
            }

            System.out.println();
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == 2 || field[i][j] == 3) field[i][j] = 1;
                else field[i][j] = 0;
            }
        }

        System.out.println("-------------------------------------------");
    }

    static boolean checkOnDeath() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == 1) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Enter size: ");
        size = scan.nextInt();

        field = new int[size][size];

        System.out.print("Enter modecode (1-random, 2-hand): ");
        int mode = scan.nextInt();

        if (mode == 1) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    field[i][j] = rand.nextInt(2);
                }
            }
        }

        int x, y;
        while (mode == 2) {
            System.out.print("Enter cords or -1: ");

            x = scan.nextInt();
            if (x == -1) break;

            y = scan.nextInt();

            if (x > size-1 || y > size-1) {
                System.out.println("Enter cords less then "+size);
                continue;
            }

            field[y][x] = field[y][x] == 0 ? 1 : 0;
        }

        while (!checkOnDeath()) {
            updateField();
            Thread.sleep(500);
        }
    }
}