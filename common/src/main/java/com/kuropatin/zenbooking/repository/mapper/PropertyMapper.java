package com.kuropatin.zenbooking.repository.mapper;

import com.kuropatin.zenbooking.model.Property;
import com.kuropatin.zenbooking.model.PropertyType;
import com.kuropatin.zenbooking.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class PropertyMapper implements RowMapper<Property> {

    @Override
    public Property mapRow(final ResultSet resultSet, final int i) throws SQLException {
        final Property property = new Property();
        property.setId(resultSet.getLong("id"));
        final User user = new User();
        user.setId(resultSet.getLong("user_id"));
        property.setUser(user);
        property.setType(PropertyType.valueOf(resultSet.getString("type")));
        property.setName(resultSet.getString("name"));
        property.setDescription(resultSet.getString("description"));
        property.setAddress(resultSet.getString("address"));
        property.setPrice(resultSet.getInt("price"));
        property.setGuests(resultSet.getShort("guests"));
        property.setRooms(resultSet.getShort("rooms"));
        property.setBeds(resultSet.getShort("beds"));
        property.setHasKitchen(resultSet.getBoolean("has_kitchen"));
        property.setHasWasher(resultSet.getBoolean("has_washer"));
        property.setHasTv(resultSet.getBoolean("has_tv"));
        property.setHasInternet(resultSet.getBoolean("has_internet"));
        property.setIsPetsAllowed(resultSet.getBoolean("is_pets_allowed"));
        property.setIsAvailable(resultSet.getBoolean("is_available"));
        property.setIsDeleted(resultSet.getBoolean("is_deleted"));
        property.setCreated(resultSet.getTimestamp("created"));
        property.setUpdated(resultSet.getTimestamp("updated"));
        return property;
    }
}