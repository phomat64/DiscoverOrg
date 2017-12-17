package com.discoverorg.ras;

import java.util.List;

import com.discoverorg.models.Item;

public interface FridgeItemRepository extends Repository<Item, String> {
	
	public List<Item> findByItemType(long itemType);
		
}
