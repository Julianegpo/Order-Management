package com.jegeap.util;

public enum OrderStatusEnum {
	
	CREATED,
	SHIPPED,
	COMPLETED,
	CANCELLED;
	
	public static String asString(OrderStatusEnum status) {
        return status != null ? status.toString() : "UNKNOWN";
    }	
	
}
