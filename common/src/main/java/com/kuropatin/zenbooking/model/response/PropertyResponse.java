package com.kuropatin.zenbooking.model.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuropatin.zenbooking.model.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PropertyResponse {

    private long id;
    private PropertyType type;
    private String name;
    private String description;
    private String address;
    private int price;
    private short guests;
    private short rooms;
    private short beds;
    private boolean hasKitchen;
    private boolean hasWasher;
    private boolean hasTv;
    private boolean hasInternet;
    private boolean isPetsAllowed;
    private boolean isAvailable;
    private String rating;

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}