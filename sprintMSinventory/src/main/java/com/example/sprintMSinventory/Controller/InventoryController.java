package com.example.sprintMSinventory.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprintMSinventory.Entity.InventoryItem;
import com.example.sprintMSinventory.Service.InventoryService;

@RestController
@RequestMapping("api/v1/inventory")
public class InventoryController {

  private InventoryService inventoryService;

  public InventoryController(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }
  
  @GetMapping
  private List<InventoryItem> getAllInventoryItems() {
    return inventoryService.getAllInventoryItems();
  }

}
