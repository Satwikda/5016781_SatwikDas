import java.util.Arrays;
import java.util.Comparator;

public class ProductSearch {

    public static void main(String[] args) {
        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Shampoo", "Beauty"),
            new Product(3, "Sofa", "Furniture"),
            new Product(4, "Television", "Electronics"),
            new Product(5, "Toothpaste", "Beauty")
        };

        // Linear search
        Product result = linearSearch(products, "Sofa");
        if (result != null) {
            System.out.println("Linear Search Result: " + result);
        } else {
            System.out.println("Product not found (Linear Search).");
        }

        // Binary search
        Arrays.sort(products, Comparator.comparing(product -> product.productName));
        result = binarySearch(products, "Sofa");
        if (result != null) {
            System.out.println("Binary Search Result: " + result);
        } else {
            System.out.println("Product not found (Binary Search).");
        }
    }

    public static Product linearSearch(Product[] products, String productName) {
        for (Product product : products) {
            if (product.productName.equals(productName)) {
                return product;
            }
        }
        return null;
    }

    public static Product binarySearch(Product[] products, String productName) {
        int left = 0;
        int right = products.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = productName.compareTo(products[mid].productName);
            if (result == 0) {
                return products[mid];
            }
            if (result > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}