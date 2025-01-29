package mobilecontacts.service;

import mobilecontacts.dto.MobileContactInsertDTO;
import mobilecontacts.dto.MobileContactUpdateDTO;
import mobilecontacts.exceptions.ContactNotFoundException;
import mobilecontacts.exceptions.PhoneNumberAlreadyExistsException;
import mobilecontacts.model.MobileContact;

import java.util.List;

public interface IMobileContactService {

    MobileContact insertMobileContact(MobileContactInsertDTO dto) throws PhoneNumberAlreadyExistsException;
    MobileContact updateMobileContact(MobileContactUpdateDTO dto)
            throws PhoneNumberAlreadyExistsException, ContactNotFoundException;
    void DeleteContactById(Long id) throws ContactNotFoundException;
    MobileContact getContactById(Long id) throws ContactNotFoundException;
    List<MobileContact> getAllContacts();

    MobileContact getContactByPhoneNUmber(String phoneNumber) throws ContactNotFoundException;
    void deleteContactByPhoneNumber(String phoneNumber) throws ContactNotFoundException;


}
