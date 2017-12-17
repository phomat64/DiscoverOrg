package com.discoverorg.ras.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.discoverorg.models.Item;
import com.discoverorg.ras.FridgeItemRepository;

public class FridgeItemRepositoryImpl implements FridgeItemRepository {
	
	private final static String DATA_FILE = "./src/resources/data_file.txt";
	
	private Map<String, Item> itemMap;
	
	public FridgeItemRepositoryImpl() {
		this.itemMap = new HashMap<>();
		populateItemData();
	}
	
	private void populateItemData() {
		String fileName = DATA_FILE;

		try (Scanner scanner = new Scanner(new File(fileName))) {
			while (scanner.hasNext()){
				Item currentItem = new Item(scanner.next(), 
						   scanner.next(), 
						   scanner.nextLong(), 
						   scanner.nextDouble(), 
						   scanner.nextBoolean());
				this.itemMap.put(currentItem.getItemUUID(), currentItem);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Item findOne(String id) {
		return this.itemMap.get(id);
	}

	@Override
	public List<Item> findAll() {
		return this.itemMap.values().stream().collect(Collectors.toList());
	}

	@Override
	public Long count() {
		return Long.valueOf(this.itemMap.size());
	}

	@Override
	public void delete(String uuid) {
		this.itemMap.remove(uuid);
	}

	@Override
	public boolean exists(String primaryKey) {
		return this.itemMap.containsKey(primaryKey);
	}
	
	public List<Item> findByItemType(long itemType) {
		return this.itemMap.values().stream().filter(item -> {
			return itemType == item.getItemType();
		}).collect(Collectors.toList());

	}

	@Override
	public void save(Item entity) {
		this.itemMap.put(entity.getItemUUID(), entity);
	}
	
	public void save(List<Item> entityList) {
		entityList.forEach(item -> {
			this.itemMap.put(item.getItemUUID(), item);
		});
	}

}
