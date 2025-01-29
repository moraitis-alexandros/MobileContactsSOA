package validation;

import mobilecontacts.dto.MobileContactInsertDTO;
import mobilecontacts.dto.MobileContactUpdateDTO;

public class ValidationUtil {
    /**
     * No instances in this class should be available
     */
    private ValidationUtil() {

    }


    public static String validateDTO(MobileContactInsertDTO insertDTO) {
        String errorResponse = "";

        if (insertDTO.getPhoneNumber().length() <= 5) errorResponse += "Phone Number must have more than 5 digits";
        if (insertDTO.getFirstname().length() < 2) errorResponse += "Firstname must have more than 2 chars";
        if (insertDTO.getLastname().length() < 2) errorResponse += "Lastname must have more than 2 chars";
        return errorResponse;
    }

    public static String validateDTO(MobileContactUpdateDTO updateDTO) {
        String errorResponse = "";

        if (updateDTO.getPhoneNumber().length() <= 5) errorResponse += "Phone Number must have more than 5 digits";
        if (updateDTO.getFirstname().length() < 2) errorResponse += "Firstname must have more than 2 chars";
        if (updateDTO.getLastname().length() < 2) errorResponse += "Lastname must have more than 2 chars";
        return errorResponse;
    }


}
