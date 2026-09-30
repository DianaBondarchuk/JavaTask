import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;

public class Array<T extends Number & Comparable<T>> {
    private T[] data;
    private final Function<Double, T> converter; // Для конвертації введення/рандому в тип T

    @SuppressWarnings("unchecked")
    public Array(int size, Function<Double, T> converter) {
        this.data = (T[]) new Number[size];
        this.converter = converter;
    }

    public void fillFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть " + data.length + " елементів:");
        for (int i = 0; i < data.length; i++) {
            data[i] = converter.apply(scanner.nextDouble());
        }
    }

    public void fillRandom(double min, double max) {
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            double val = min + (max - min) * random.nextDouble();
            data[i] = converter.apply(val);
        }
    }

    public void print() {
        System.out.println(Arrays.toString(data));
    }

    public T getMax() {
        T max = data[0];
        for (T val : data) {
            if (val.compareTo(max) > 0) max = val;
        }
        return max;
    }

    public T getMin() {
        T min = data[0];
        for (T val : data) {
            if (val.compareTo(min) < 0) min = val;
        }
        return min;
    }

    public double getAverage() {
        double sum = 0;
        for (T val : data) {
            sum += val.doubleValue();
        }
        return sum / data.length;
    }

    public void sortAscending() {
        Arrays.sort(data);
    }

    public void sortDescending() {
        Arrays.sort(data, (a, b) -> b.compareTo(a));
    }

    public int binarySearch(T key) {
        sortAscending(); 
        return Arrays.binarySearch(data, key);
    }

    public void replace(int index, T newValue) {
        if (index >= 0 && index < data.length) {
            data[index] = newValue;
        } else {
            System.out.println("Індекс виходить за межі масиву!");
        }
    }
}
