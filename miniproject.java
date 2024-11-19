package apparelsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Apparel {
    public String productId;
    public String productName;
    public String details;
    public String dimensions;
    public String shade;
    public double pricePerUnit;
    public int stockAvailable;

    
    public Apparel(String productId, String productName, String details, String dimensions, String shade, double pricePerUnit, int stockAvailable) {
        this.productId = productId;
        this.productName = productName;
        this.details = details;
        this.dimensions = dimensions;
        this.shade = shade;
        this.pricePerUnit = pricePerUnit;
        this.stockAvailable = stockAvailable;
    }

    void updateStock(int updatedStock) {
        this.stockAvailable = updatedStock;
    }

    double getDiscountedPrice(double discountPercentage) {
        return pricePerUnit - (pricePerUnit * discountPercentage / 100);
    }
}

class Textile {
    public String textileId;
    public String category;
    public String colorShade;
    public double costPerUnit;

    
    public Textile(String textileId, String category, String colorShade, double costPerUnit) {
        this.textileId = textileId;
        this.category = category;
        this.colorShade = colorShade;
        this.costPerUnit = costPerUnit;
    }

    double calculateCost(double length) {
        return length * costPerUnit;
    }
}

class Distributor {
    public String distributorId;
    public String distributorName;
    public String contact;
    List<Textile> suppliedTextiles;

    
    public Distributor(String distributorId, String distributorName, String contact) {
        this.distributorId = distributorId;
        this.distributorName = distributorName;
        this.contact = contact;
        this.suppliedTextiles = new ArrayList<>();
    }

    void provideTextile(Textile textile) {
        suppliedTextiles.add(textile);
    }

    List<Textile> getSuppliedTextiles() {
        return suppliedTextiles;
    }
}

class Transaction {
    public String transactionId;
    public Date transactionDate;
    public List<Apparel> purchasedItems;
    private double totalCost;

    
    public Transaction(String transactionId) {
        this.transactionId = transactionId;
        this.transactionDate = new Date();
        this.purchasedItems = new ArrayList<>();
    }

    void addItem(Apparel item) {
        purchasedItems.add(item);
    }

    double computeTotalCost() {
        totalCost = 0; // Reset total before computation
        for (Apparel item : purchasedItems) {
            totalCost += item.pricePerUnit;
        }
        return totalCost;
    }

    void showTransactionDetails() {
        System.out.println("----- Transaction Summary -----");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Date: " + transactionDate);
        for (Apparel item : purchasedItems) {
            System.out.println("Item: " + item.productName + " | Price: $" + item.pricePerUnit);
        }
        System.out.println("Total Cost: $" + totalCost);
    }
}

class Buyer {
    public String buyerId;
    public String name;
    public String email;
    public String phone;

    
    public Buyer(String buyerId, String name, String email, String phone) {
        this.buyerId = buyerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    void completeTransaction(Transaction transaction) {
        transaction.showTransactionDetails();
        System.out.println("Transaction Completed!");
    }
}

class InventorySystem {
    List<Apparel> inventory;

    public InventorySystem() {
        inventory = new ArrayList<>();
    }

    void addItem(Apparel item) {
        inventory.add(item);
    }

    void removeItem(String productId) {
        inventory.removeIf(item -> item.productId.equals(productId));
    }
    Apparel findItem(String productId) {
        for (Apparel item : inventory) {
            if (item.productId.equals(productId)) {
                return item;
            }
        }
        return null;
    }
}

public class ApparelSystem {
    public static void main(String[] args) {
        Apparel item1 = new Apparel("A101", "Silk Top", "Soft and luxurious", "M", "Pink", 30.00, 100);
        Apparel item2 = new Apparel("A102", "Linen Pants", "Breathable and comfortable", "L", "Gray", 45.00, 50);

        InventorySystem inventory = new InventorySystem();
        inventory.addItem(item1);
        inventory.addItem(item2);

        double discountedPrice = item1.getDiscountedPrice(10);
        System.out.println("Discounted Price for " + item1.productName + ": $" + discountedPrice);

        Buyer buyer = new Buyer("B001", "Jane Smith", "jane.smith@example.com", "987-654-3210");
        Transaction transaction = new Transaction("T001");
        transaction.addItem(item1);
        buyer.completeTransaction(transaction);
    }
}
    
