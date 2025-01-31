package mobilecontacts.controller;

import mobilecontacts.core.serializer.Serializer;
import mobilecontacts.dao.IMobileContactDAO;
import mobilecontacts.dao.MobileContactDAOImpl;
import mobilecontacts.dto.MobileContactInsertDTO;
import mobilecontacts.dto.MobileContactReadOnlyDTO;
import mobilecontacts.dto.MobileContactUpdateDTO;
import mobilecontacts.exceptions.ContactNotFoundException;
import mobilecontacts.exceptions.PhoneNumberAlreadyExistsException;
import mobilecontacts.mapper.MapperUtil;
import mobilecontacts.model.MobileContact;
import mobilecontacts.service.IMobileContactService;
import mobilecontacts.service.MobileContactServiceImpl;
import validation.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

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
            readOnlyDTO = MapperUtil.mapMobileContactToDTO(mobileContact);

            return "OK\n" + Serializer.serializeDTO(readOnlyDTO);
        } catch (PhoneNumberAlreadyExistsException e) {
            return  "Error\n" + e.getMessage() + "\n";
        }
    }


    public String updateContact(MobileContactUpdateDTO updateDTO) {
        try {
            //Validate input data
            String errors = ValidationUtil.validateDTO(updateDTO);
            MobileContact mobileContact;
            MobileContactReadOnlyDTO readOnlyDTO;

            if (!errors.isEmpty()) {
                return "Error. \n" + "Validation error \n" + errors;
            }

            //if validation ok, insert contact
            mobileContact = service.updateMobileContact(updateDTO);
            readOnlyDTO = MapperUtil.mapMobileContactToDTO(mobileContact);

            return "OK\n" + Serializer.serializeDTO(readOnlyDTO);
        } catch (PhoneNumberAlreadyExistsException  e) {
            return  "Error\n" + e.getMessage() + "\n";
        } catch (ContactNotFoundException e) {
            return  "Error \n" + e.getMessage() + "\n";
        }
    }

    public String deleteContactById(Long id) {
        try {
            service.deleteContactById(id);
            return "OK\n Contact Deleted";

        } catch (ContactNotFoundException e) {
            return  "Error . Contact not found. \n";
        }
    }

    public String getContactById(Long id) {
        MobileContact mobileContact;
        MobileContactReadOnlyDTO readOnlyDTO;
        try {
            mobileContact = service.getContactById(id);
            readOnlyDTO = MapperUtil.mapMobileContactToDTO(mobileContact);
            return Serializer.serializeDTO(readOnlyDTO);


        } catch (ContactNotFoundException e) {
            return  "Error. Contact not found\n";
        }
    }

    public List<String> getAllContacts() {
        List<MobileContact> contacts;
        List<String> serializedList = new ArrayList<>();
        contacts = service.getAllContacts();

        for (MobileContact mobileContact : contacts) {
            serializedList.add(Serializer.serializeDTO(MapperUtil.mapMobileContactToDTO(mobileContact)));
        }
        return serializedList;
    }

    public String getContactByPhoneNumber(String phoneNumber) {
        MobileContact mobileContact;
        MobileContactReadOnlyDTO readOnlyDTO;
        try {
            mobileContact = service.getContactByPhoneNUmber(phoneNumber);
            readOnlyDTO = MapperUtil.mapMobileContactToDTO(mobileContact);
            return Serializer.serializeDTO(readOnlyDTO);


        } catch (ContactNotFoundException e) {
            return  "Error. Contact not found\n";
        }
    }

    public String deleteContactByPhoneNumber(String phoneNumber) {
        try {
            service.deleteContactByPhoneNumber(phoneNumber);
            return "OK\n Contact Deleted";

        } catch (ContactNotFoundException e) {
            return  "Error . Contact not found. \n";
        }
    }





}
