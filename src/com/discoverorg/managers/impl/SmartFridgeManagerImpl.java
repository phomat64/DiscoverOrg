package com.discoverorg.managers.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.discoverorg.managers.SmartFridgeManager;
import com.discoverorg.models.Item;
import com.discoverorg.ras.FridgeItemRepository;
import com.discoverorg.ras.impl.FridgeItemRepositoryImpl;

public class SmartFridgeManagerImpl implements SmartFridgeManager {

	// Mock spring jpa repository
	private FridgeItemRepository fridgeItemRepository = new FridgeItemRepositoryImpl();

	@Override
	public void handleItemRemoved(String itemUUID) {
		notify(itemUUID + " has been removed.");
	}

	@Override
	public void handleItemAdded(long itemType, String itemUUID, String name, Double fillFactor) {
		String msg = "Item{" +
						"itemUUID='" + itemUUID + '\'' +
						", name='" + name + '\'' +
						", itemType='" + itemType + '\'' +
						", fillFactor='" + fillFactor + '\'' +
						"}";
		notify("Item added:\n" + msg);
	}
	
	@Override
	public Object[] getItems(Double fillFactor) {
		// get list of all item types
		List<Long> itemTypes = this.fridgeItemRepository
				.findAll()
				.stream()
				.map(item -> { 
					return item.getItemType(); 
				}).distinct()
				.collect(Collectors.toList());
		
		// Create main Object array
		Object[] itemObjArr = itemTypes.stream().map(itemType -> {
			// as per requirement: @return an array of arrays containing [ itemType, fillFactor ]...
			return new Object[] { new Object[] { itemType, this.getFillFactor(itemType) } };
		})
		.collect(Collectors.toList())
		.toArray();
		
		// check if empty
		boolean allEmpty = Arrays.stream(itemObjArr)
				.allMatch(currentObjArr -> {
					Object[] parentObjArr = (Object[])currentObjArr;
					Object[] nestedObjArr = (Object[])parentObjArr[0];
					return (Double)nestedObjArr[1] == 0.0;
				});
		
		// As per requirement: Unless all available containers are empty,
		// this method should only consider the non-empty containers (fillFactor > 0)
		if (allEmpty) {
			return itemObjArr;
		} else {
			//return non-empty containers
			return Arrays.stream(itemObjArr)
					.filter(itemObj -> {
						double currentFillFactor = extractFillFactorFromObjArr(itemObj);
						return currentFillFactor > 0.0 && currentFillFactor <= fillFactor;
					}).collect(Collectors.toList()).toArray();
		}
	}
	
	private Double extractFillFactorFromObjArr(Object itemObj) {
		Object[] parentObjArr = (Object[])itemObj;
		Object[] nestedObjArr = (Object[])parentObjArr[0];
		return (Double)nestedObjArr[1];
	}

	@Override
	public Double getFillFactor(long itemType) {
		List<Item> itemList = this.fridgeItemRepository.findByItemType(itemType);
		boolean allEmpty = itemList.stream().allMatch(item -> {
			return item.getFillFactor() == 0;
		});
		
		// Satisfying requirement: Unless all available containers are empty, 
		// this method should only consider the non-empty containers
		if (allEmpty) {
			return 0.0;
		} else {
			return itemList
					.stream()
					.filter(item -> {
						return item.getFillFactor() > 0.0;
					})
					.mapToDouble(item -> { return item.getFillFactor(); })
					.average()
					.getAsDouble();
		}
	}

	@Override
	public void forgetItem(long itemType) {
		List<Item> itemList = this.fridgeItemRepository.findByItemType(itemType);
		itemList.forEach(item -> {
			item.setInStock(false);
		});
		this.fridgeItemRepository.save(itemList);
	}
	
	public void addItem(String itemUUID, String name, long itemType, Double fillFactor) {
		this.fridgeItemRepository.save(new Item(itemUUID, name, itemType, fillFactor, true));
		this.handleItemAdded(itemType, itemUUID, name, fillFactor);
	}
	
	public void removeItem(String uuid) {
		this.fridgeItemRepository.delete(uuid);
		this.handleItemRemoved(uuid);
	}
	
	private void notify(String message) {
		System.out.println(message);
	}

}
