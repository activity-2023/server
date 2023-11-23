package fr.cyu.depinfo.activity.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DoorStatusConverter implements AttributeConverter<DoorStatus, Boolean> {
    @Override
    public Boolean convertToDatabaseColumn(DoorStatus attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getDoorOpened();
    }

    @Override
    public DoorStatus convertToEntityAttribute(Boolean dbData) {
        if (dbData == null) {
            return null;
        }
        return DoorStatus.fromDoorOpened(dbData);
    }
}
