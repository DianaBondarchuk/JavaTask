public class Main {
    public static void main(String[] args) {
        Array<Double> arr = new Array<>(5, Double::valueOf);
        arr.fillRandom(1.0, 50.0);
        System.out.print("Початковий масив: ");
        arr.print();
        
        arr.sortAscending();
        System.out.print("Відсортований за зростанням: ");
        arr.print();
        
        System.out.println("Max: " + arr.getMax());
        System.out.println("Min: " + arr.getMin());
        System.out.println("Average: " + arr.getAverage());

        Matrix<Integer> m1 = new Matrix<>(2, 2, Double::intValue);
        Matrix<Integer> m2 = new Matrix<>(2, 2, Double::intValue);
        
        m1.fillRandom(1, 10);
        m2.fillRandom(1, 10);
        
        System.out.println("\nМатриця 1:");
        m1.print();
        System.out.println("Матриця 2:");
        m2.print();
        
        System.out.println("Результат додавання:");
        m1.add(m2).print();
    }
}