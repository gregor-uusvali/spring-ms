package com.example.sprintMSinventory.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sprintMSinventory.Entity.InventoryItem;
import com.example.sprintMSinventory.Repository.InventoryRepository;

@Service
public class InventoryService {

  private InventoryRepository inventoryRepository;

  public InventoryService(InventoryRepository inventoryRepository) {
    this.inventoryRepository = inventoryRepository;
  }

  public List<InventoryItem> getAllInventoryItems() {
    return inventoryRepository.findAll();
  }

}
