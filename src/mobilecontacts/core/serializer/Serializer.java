package mobilecontacts.core.serializer;

import mobilecontacts.dto.MobileContactReadOnlyDTO;

public class Serializer {

    /**
     * No instances on this class should be available
     */
    private Serializer() {

    }

    public static String serializeDTO(MobileContactReadOnlyDTO readOnlyDTO) {
        return "ID: " + readOnlyDTO.getId() + ", Firstname: " + readOnlyDTO.getFirstname() + ", Lastname: " + readOnlyDTO.getLastname() + ", Phone number: " + readOnlyDTO.getPhoneNumber();
    }

}
