package com.discoverorg;

import com.discoverorg.managers.impl.SmartFridgeManagerImpl;

public class Application {
	
	public static void main(String[] args) {
		SmartFridgeManagerImpl mgr = new SmartFridgeManagerImpl();
		
		// Get items with fill factor that is equal or less to value specified
		// Get item types with fillFactor of 80% and below
		Object[] objArr = mgr.getItems(0.8);
		printObjArr(objArr);
		
		// Test handleItemRemoved
		// remove ketchup
		mgr.removeItem("765-4265-886");
		
		// Get fill factor for itemType 13 (condiments)
		System.out.println("fillFactor: " + mgr.getFillFactor(13));
		
		// Forget item 13 
		mgr.forgetItem(13);
		
		// Get item types with fillFactor of 50% and below
		objArr = mgr.getItems(0.5);
		printObjArr(objArr);
		
		// Add item
		mgr.addItem("555-5555-555", "Lamb", 22, 0.4);
		
		// Get item types with fillFactor of 100% and below
		objArr = mgr.getItems(1.0);
		printObjArr(objArr);
	}
	
	// Since getItems() returns an Array of Arrays, we must dig down to get the value.
	private static void printObjArr(Object[] objArr) {
		for (Object obj1 : objArr) {
			Object[] tempArr = (Object[])obj1;
			Object[] nestedTempArr = (Object[])tempArr[0];
			System.out.println("[" + nestedTempArr[0] + ", " + nestedTempArr[1] + "]");
		}
	}
	
}
