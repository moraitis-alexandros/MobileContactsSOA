package mobilecontacts.controller;

import mobilecontacts.dao.IMobileContactDAO;
import mobilecontacts.dao.MobileContactDAOImpl;
import mobilecontacts.dto.MobileContactInsertDTO;
import mobilecontacts.dto.MobileContactReadOnlyDTO;
import mobilecontacts.exceptions.PhoneNumberAlreadyExistsException;
import mobilecontacts.model.MobileContact;
import mobilecontacts.service.IMobileContactService;
import mobilecontacts.service.MobileContactServiceImpl;
import validation.ValidationUtil;

public class MobileContactController {

    private final IMobileContactDAO dao = new MobileContactDAOImpl();
    private final IMobileContactService service = new MobileContactServiceImpl(dao);


    public String insertContact(MobileContactInsertDTO insertDTO) {
        try {
            //Validate input data
            String errors = ValidationUtil.validateDTO(insertDTO);
            MobileContact mobileContact;
            MobileContactReadOnlyDTO readOnlyDTO;

            if (!errors.isEmpty()) {
                return "Error. " + "Validation error \n" + errors;
            }

            //if validation ok, insert contact
            mobileContact = service.insertMobileContact(insertDTO);
            readOnlyDTO = mapMobileContactToDTO(mobileContact);

            return "OK\n" + serializeDTO(readOnlyDTO);
        } catch (PhoneNumberAlreadyExistsException e) {
            return  "Error\n" + e.getMessage() + "\n";
        }
    }



    private MobileContactReadOnlyDTO mapMobileContactToDTO(MobileContact mobileContact) {
        return new MobileContactReadOnlyDTO(mobileContact.getId(), mobileContact.getFirstName(), mobileContact.getLastName(), mobileContact.getPhoneNumber());
    }

    private String serializeDTO(MobileContactReadOnlyDTO readOnlyDTO) {
        return "ID: " + readOnlyDTO.getId() + ", Firstname: " + readOnlyDTO.getFirstname() + ", Lastname: " + readOnlyDTO.getLastname() + ", Phonenumber: " + readOnlyDTO.getPhoneNumber();
    }



}
