package com.example;

import com.example.entity.Inventory;
import com.example.service.InventoryService;
import com.example.service.InventoryServiceImpl;
import org.junit.jupiter.api.*;
import com.example.util.DBConnectionUtil;
import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {
    static InventoryService service = new InventoryServiceImpl();
    static int testItemId;

    // 1. File Existence Check (Non-functional)
    @Test @Order(1)
    void test_Util_File_Exist() {
        File file = new File("src/main/java/com/example/util/DBConnectionUtil.java");
        Assertions.assertTrue(file.exists(), "exists");
    }

    // 2. Folder Structure Validation (Non-functional)
    @Test @Order(2)
    void test_Util_Folder_Exist() {
        File folder = new File("src/main/java/com/example/util");
        Assertions.assertTrue(folder.isDirectory(), "exists");
    }

    // 3. Method Existence Check (Non-functional)
    @Test @Order(3)
    void test_Check_Method_Exist() throws Exception {
        Class<?> clazz = Class.forName("com.example.service.InventoryServiceImpl");
        Method add = clazz.getMethod("addItem", Inventory.class);
        Method del = clazz.getMethod("deleteItem", int.class);
        Method get = clazz.getMethod("getAllItems");
        Assertions.assertNotNull(add, "addItem exists");
        Assertions.assertNotNull(del, "deleteItem exists");
        Assertions.assertNotNull(get, "getAllItems exists");
    }

    // 4. Add Inventory Item Execution (Functional)
    @Test @Order(4)
    void Test_Create_Query_Exist() {
        Inventory item = new Inventory(0, "TestItem", "TestCategory", 5, 49.99);
        String result = service.addItem(item);
        Assertions.assertTrue(result.contains("success"));

        // Get ID for deletion
        List<Inventory> list = service.getAllItems();
        testItemId = list.get(list.size() - 1).getItemId();
    }

    // 5. Delete Inventory Item Execution (Functional)
    @Test @Order(5)
    void Test_Delete_Query_Exist() {
        Inventory item = new Inventory(0, "DemoItem", "DemoCategory", 10, 99.99);
        String addResult = service.addItem(item);
        Assertions.assertTrue(addResult.contains("added"));

        List<Inventory> items = service.getAllItems();
        int itemId = items.get(items.size() - 1).getItemId();

        String deleteResult = service.deleteItem(itemId);
        Assertions.assertTrue(deleteResult.contains("deleted"));
    }
}
