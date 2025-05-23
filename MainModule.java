package com.example;

import com.example.entity.Inventory;
import com.example.service.InventoryService;
import com.example.service.InventoryServiceImpl;

import java.util.List;
import java.util.Scanner;

public class MainModule {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        InventoryService inventoryService = new InventoryServiceImpl();
        int choice;
        do {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Item");
            System.out.println("2. Delete Item");
            System.out.println("3. View All Items");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addItem(inventoryService);
                case 2 -> deleteItem(inventoryService);
                case 3 -> viewAllItems(inventoryService);
                case 4 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 4);
    }

    private static void addItem(InventoryService service) {
        System.out.print("Enter Item Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int qty = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Inventory item = new Inventory(0, name, category, qty, price);
        System.out.println(service.addItem(item));
    }

    private static void deleteItem(InventoryService service) {
        System.out.print("Enter Item ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println(service.deleteItem(id));
    }

    private static void viewAllItems(InventoryService service) {
        List<Inventory> items = service.getAllItems();
        if (items.isEmpty()) {
            System.out.println("No items found.");
        } else {
            System.out.println("Item ID | Name | Category | Quantity | Price");
            for (Inventory item : items) {
                System.out.println(item);
            }
        }
    }
}
