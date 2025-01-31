package mobilecontacts.service;

import mobilecontacts.dao.IMobileContactDAO;
import mobilecontacts.dto.MobileContactInsertDTO;
import mobilecontacts.dto.MobileContactReadOnlyDTO;
import mobilecontacts.dto.MobileContactUpdateDTO;
import mobilecontacts.exceptions.ContactNotFoundException;
import mobilecontacts.exceptions.PhoneNumberAlreadyExistsException;
import mobilecontacts.mapper.MapperUtil;
import mobilecontacts.model.MobileContact;

import java.util.List;

public class MobileContactServiceImpl implements IMobileContactService {

    private final IMobileContactDAO dao;

    public MobileContactServiceImpl(IMobileContactDAO dao) {
        this.dao = dao;
    }

    @Override
    public MobileContact insertMobileContact(MobileContactInsertDTO dto)
            throws PhoneNumberAlreadyExistsException {
        MobileContact mobileContact;

        try {
            if (dao.phoneNumberExists(dto.getPhoneNumber())) {
                throw new PhoneNumberAlreadyExistsException("Contact with phone number " + dto.getPhoneNumber() + "already exists. \n");
            }
            mobileContact = MapperUtil.mapInsertDTOToContact(dto);
            System.err.printf("MobileContactServiceImpl Logger: %s was inserted \n", mobileContact);
            return dao.insert(mobileContact);
        } catch (PhoneNumberAlreadyExistsException e) {
            System.err.printf("MobileContactServiceImpl Logger: contact with phone number: %s already exists \n", dto.getPhoneNumber());
            throw e;
        }
    }

    @Override
    public MobileContact updateMobileContact(MobileContactUpdateDTO dto)
            throws PhoneNumberAlreadyExistsException, ContactNotFoundException {
        MobileContact mobileContact;
        MobileContact newContact;
        try {
            if(!dao.userIdExists(dto.getId())) {
                throw new ContactNotFoundException(
                        "contact with id: " + dto.getId() + " not found for update"
                );
            }
        mobileContact = dao.getById(dto.getId());

            boolean isPhoneNumberOurOwn = mobileContact.getPhoneNumber().equals(dto.getPhoneNumber());
            boolean isPhoneNumberExists = dao.phoneNumberExists(dto.getPhoneNumber());

            if (isPhoneNumberExists && !isPhoneNumberOurOwn) {
                throw new PhoneNumberAlreadyExistsException("Contact with phone number " + dto.getPhoneNumber() + " already exists and cannot be updated.\n");
            }

            newContact = MapperUtil.mapUpdateDTOToContact(dto);
            System.err.printf("MobileContactServiceImpl Logger: %s was updated with new info: %s", mobileContact, newContact);
            return dao.update(dto.getId(), newContact);

        } catch (ContactNotFoundException | PhoneNumberAlreadyExistsException e) {
            System.err.println("MobileContactServiceImpl Logger: contact with id: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteContactById(Long id) throws ContactNotFoundException {
        try {
            if(!dao.userIdExists(id)) {
                throw new ContactNotFoundException("Contact with id: " + id + " not found for delete.");
            }
            System.err.println("MobileContactServiceImpl Logger: contact with id: " + id + " was deleted");
            dao.deleteById(id);
        } catch (ContactNotFoundException e) {
            System.err.println("MobileContactServiceImpl Logger: contact with id: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public MobileContact getContactById(Long id) throws ContactNotFoundException {
        MobileContact mobileContact;
        try {
            mobileContact = dao.getById(id);
            if (mobileContact == null) {
                throw new ContactNotFoundException("Contant with id: " + id + " not found.");
            }
            return mobileContact;
        } catch (ContactNotFoundException e) {
            System.err.println("Contact with id: " + id + " was not found to get returned.");
            throw e;
        }
    }

    @Override
    public List<MobileContact> getAllContacts() {
        return dao.getAll();
    }

    @Override
    public MobileContact getContactByPhoneNUmber(String phoneNumber) throws ContactNotFoundException {
        MobileContact mobileContact;
        try {
            mobileContact = dao.getByPhoneNumber(phoneNumber);
            if (mobileContact == null) {
                throw new ContactNotFoundException("Contact with phone number: " + phoneNumber + " not found.");
            }
            return mobileContact;
        } catch (ContactNotFoundException e) {
            System.err.println("Contact with phone number: " + phoneNumber + " was not found to get returned.");
            throw e;
        }
    }


    @Override
    public void deleteContactByPhoneNumber(String phoneNumber) throws ContactNotFoundException {
        try {
            if(!dao.phoneNumberExists(phoneNumber)) {
                throw new ContactNotFoundException("Contact with phone number: " + phoneNumber + " not found for delete.");
            }
            System.err.println("MobileContactServiceImpl Logger: contact with phone number: " + phoneNumber + " was deleted");
            dao.deleteByPhoneNumber(phoneNumber);
        } catch (ContactNotFoundException e) {
            System.err.println("MobileContactServiceImpl Logger: contact with phone number: " + e.getMessage());
            throw e;
        }
    }



}
