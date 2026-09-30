import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;

public class Matrix<T extends Number & Comparable<T>> {
    private final int rows;
    private final int cols;
    private final Object[][] data;
    private final Function<Double, T> converter;

    public Matrix(int rows, int cols, Function<Double, T> converter) {
        this.rows = rows;
        this.cols = cols;
        this.data = new Object[rows][cols];
        this.converter = converter;
    }

    @SuppressWarnings("unchecked")
    public T get(int r, int c) {
        return (T) data[r][c];
    }

    public void set(int r, int c, T val) {
        data[r][c] = val;
    }

    public void fillFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Заповніть матрицю " + rows + "x" + cols + ":");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = converter.apply(scanner.nextDouble());
            }
        }
    }

    public void fillRandom(double min, double max) {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = converter.apply(min + (max - min) * random.nextDouble());
            }
        }
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%8.2f ", get(i, j).doubleValue());
            }
            System.out.println();
        }
    }

    public Matrix<T> add(Matrix<T> other) {
        Matrix<T> result = new Matrix<>(rows, cols, converter);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double res = get(i, j).doubleValue() + other.get(i, j).doubleValue();
                result.set(i, j, converter.apply(res));
            }
        }
        return result;
    }

    public Matrix<T> subtract(Matrix<T> other) {
        Matrix<T> result = new Matrix<>(rows, cols, converter);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double res = get(i, j).doubleValue() - other.get(i, j).doubleValue();
                result.set(i, j, converter.apply(res));
            }
        }
        return result;
    }

    public Matrix<T> multiply(Matrix<T> other) {
        Matrix<T> result = new Matrix<>(this.rows, other.cols, converter);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                double sum = 0;
                for (int k = 0; k < this.cols; k++) {
                    sum += get(i, k).doubleValue() * other.get(k, j).doubleValue();
                }
                result.set(i, j, converter.apply(sum));
            }
        }
        return result;
    }

    public Matrix<T> divideElements(Matrix<T> other) {
        Matrix<T> result = new Matrix<>(rows, cols, converter);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double res = get(i, j).doubleValue() / other.get(i, j).doubleValue();
                result.set(i, j, converter.apply(res));
            }
        }
        return result;
    }

    public T getMax() {
        T max = get(0, 0);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (get(i, j).compareTo(max) > 0) max = get(i, j);
            }
        }
        return max;
    }

    public T getMin() {
        T min = get(0, 0);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (get(i, j).compareTo(min) < 0) min = get(i, j);
            }
        }
        return min;
    }

    public double getAverage() {
        double sum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum += get(i, j).doubleValue();
            }
        }
        return sum / (rows * cols);
    }
}
