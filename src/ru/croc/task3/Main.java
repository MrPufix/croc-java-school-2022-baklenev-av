package ru.croc.task3;

import java.util.Scanner;

public class Main {
    public static void swap(int[] arr, int first_index, int second_index) {
        int temp = arr[first_index];
        arr[first_index] = arr[second_index];
        arr[second_index] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите элементы массива: ");
        String input = scanner.nextLine();

        String[] elements = input.split(" ");
        int[] arr = new int[elements.length];

        int max = Integer.MIN_VALUE;
        int max_index = 0;
        int min = Integer.MAX_VALUE;
        int min_index = 0;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(elements[i]);

            if(arr[i] > max) {
                max = arr[i];
                max_index = i;
            }

            if(arr[i] < min) {
                min = arr[i];
                min_index = i;
            }
        }

        swap(arr, min_index, 0);
        if (arr[arr.length - 1] != max)
            swap(arr, max_index, arr.length - 1);

        for (int el: arr) {
            System.out.print(el + " ");
        }
    }
}
