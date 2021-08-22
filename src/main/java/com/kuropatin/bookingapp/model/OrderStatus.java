package com.kuropatin.bookingapp.model;

public class OrderStatus {

    private OrderStatus() {
    }

    public static final String ACTIVE_NOT_ACCEPTED = "NOT ACCEPTED";
    public static final String ACTIVE_ACCEPTED = "ACCEPTED";
    public static final String FINISHED = "FINISHED";
    public static final String CANCELLED = "CANCELLED";
    public static final String DECLINED = "DECLINED";
    public static final String STATUS_UNKNOWN = "STATUS UNKNOWN";
}