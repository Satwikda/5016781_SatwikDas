public class Main {
    public static void main(String[] args) {
        Order[] orders = {
                new Order(1, "Alice", 250.50),
                new Order(2, "Bob", 150.75),
                new Order(3, "Charlie", 450.00),
                new Order(4, "David", 100.25),
                new Order(5, "Eve", 200.00)
        };

        System.out.println("Original Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }

        // Bubble Sort
        Order[] bubbleSortedOrders = orders.clone();
        long bubbleStart = System.nanoTime();
        BubbleSort.bubbleSort(bubbleSortedOrders);
        long bubbleEnd = System.nanoTime();

        System.out.println("\nBubble Sorted Orders:");
        for (Order order : bubbleSortedOrders) {
            System.out.println(order);
        }
        System.out.println("Bubble Sort Time: " + (bubbleEnd - bubbleStart) + " ns");

        // Quick Sort
        Order[] quickSortedOrders = orders.clone();
        long quickStart = System.nanoTime();
        QuickSort.quickSort(quickSortedOrders, 0, quickSortedOrders.length - 1);
        long quickEnd = System.nanoTime();

        System.out.println("\nQuick Sorted Orders:");
        for (Order order : quickSortedOrders) {
            System.out.println(order);
        }
        System.out.println("Quick Sort Time: " + (quickEnd - quickStart) + " ns");
    }
}