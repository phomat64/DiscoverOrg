package com.discoverorg.models;

import java.util.Objects;

public class Item {

	private String itemUUID;
	
	private String name;
	
	private long itemType;
	
	private Double fillFactor;
	
	private boolean inStock;
	
	public Item(String itemUUID, String name, long itemType, Double fillFactor, boolean inStock) {
		this.itemUUID = itemUUID;
		this.name = name;
		this.itemType = itemType;
		this.fillFactor = fillFactor;
		this.inStock = inStock;
	}
	
	public String getItemUUID() {
		return itemUUID;
	}

	public void setItemUUID(String itemUUID) {
		this.itemUUID = itemUUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getItemType() {
		return itemType;
	}

	public void setItemType(long itemType) {
		this.itemType = itemType;
	}

	public Double getFillFactor() {
		return fillFactor;
	}

	public void setFillFactor(Double fillFactor) {
		this.fillFactor = fillFactor;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		
		if (o == null || this.getClass() != o.getClass()) {
			return false;
		}
		
		Item item = (Item) o;
		return Objects.equals(this.itemUUID, item.itemUUID) &&
				Objects.equals(this.name, item.name) &&
				this.itemType == item.itemType &&
				this.fillFactor == item.fillFactor &&
				this.inStock == item.inStock;
	}
	
	@Override
	public String toString() {
		return "Item{" +
				"itemUUID='" + this.itemUUID + '\'' +
				", name='" + name + '\'' +
				", itemType='" + itemType + '\'' +
				", fillFactor='" + fillFactor + '\'' +
				", inStock='" + inStock + '\'' +
				"}";
	}
	
}
