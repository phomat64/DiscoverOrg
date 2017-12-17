package com.discoverorg.models;

/**
 *	Represents an item's itemType and fillFactor.
 *	May use this as return type of getItems.
 */
public class ItemContainer {
	
	private long itemType;
	
	private Double fillFactor;

	public ItemContainer(long itemType, Double fillFactor) {
		this.itemType = itemType;
		this.fillFactor = fillFactor;
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
	
}
