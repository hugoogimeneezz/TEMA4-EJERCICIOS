import java.util.Scanner;

public class SelectSort {
    public static void main(String[] args) {
        int[] array = {5, 2, 7, 8, 1, 4, 9, 3, 6, 10};

        int dancing;
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            dancing = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < dancing) {
                    temp = dancing;
                    dancing = array[j];
                    array[j] = temp;

                }
            }
            array[i] = dancing;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i] + " ");
        }

    }
}
