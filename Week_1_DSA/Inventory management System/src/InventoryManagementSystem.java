import java.util.HashMap;

public class InventoryManagementSystem {
    private HashMap<Integer, Product> inventory;

    public InventoryManagementSystem() {
        inventory = new HashMap<>();
    }

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    
    public void updateProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            inventory.put(product.getProductId(), product);
        } else {
            System.out.println("Product not found in the inventory.");
        }
    }

    public void deleteProduct(int productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
        } else {
            System.out.println("Product not found in the inventory.");
        }
    }


    public void displayInventory() {
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }

    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();

        
        ims.addProduct(new Product(1, "Laptop", 10, 999.99));
        ims.addProduct(new Product(2, "Smartphone", 20, 499.99));

        
        ims.displayInventory();

        
        ims.updateProduct(new Product(1, "Laptop", 8, 999.99));

    
        ims.deleteProduct(2);

        
        ims.displayInventory();
    }
}