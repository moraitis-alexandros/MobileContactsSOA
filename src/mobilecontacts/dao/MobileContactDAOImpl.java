package mobilecontacts.dao;

import mobilecontacts.model.MobileContact;

import java.util.ArrayList;
import java.util.List;

public class MobileContactDAOImpl implements IMobileContactDAO {
    private static final List<MobileContact> contacts = new ArrayList<>(); //we use it instead of datasource (DB)
    private static Long id = 1L; //represents the autoincrement in DB

    @Override
    public MobileContact insert(MobileContact mobileContact) {
        mobileContact.setId(id++);
        contacts.add(mobileContact);
        return mobileContact;
    }

    @Override
    public MobileContact update(Long id, MobileContact mobileContact) {
        contacts.set(getIndexById(id),mobileContact);
        return mobileContact;
    }

    @Override
    public void deleteById(Long id) {
//        contacts.remove(getIndexById(id));
        contacts.removeIf(el -> el.getId().equals(id));
    }

    @Override
    public MobileContact getById(Long id) {
        return  (getIndexById(id) != -1) ? contacts.get(getIndexById(id)) : null ;
    }

    @Override
    public List<MobileContact> getAll() {
        return new ArrayList<>(contacts);
    }

    @Override
    public void deleteByPhoneNumber(String phoneNumber) {
        contacts.removeIf(el -> el.getPhoneNumber().equals(phoneNumber));

    }

    @Override
    public MobileContact getByPhoneNumber(String phoneNumber) {
        return  (getIndexByPhoneNumber(phoneNumber) != -1) ? contacts.get(getIndexByPhoneNumber(phoneNumber)) : null ;
    }

    @Override
    public boolean userIdExists(Long id) {
        return getIndexById(id) !=  -1;
    }

    @Override
    public boolean phoneNumberExists(String phoneNumber) {
        return  getIndexByPhoneNumber(phoneNumber) != -1;
    }

    private int getIndexById(Long id) {
        int positionToReturn = - 1;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId().equals(id)) {
                positionToReturn = i;
                break;
            }
        }
        return positionToReturn;
    }

    private int getIndexByPhoneNumber(String phoneNumber) {
        int positionToReturn = - 1;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhoneNumber().equals(phoneNumber)) {
                positionToReturn = i;
                break;
            }
        }
        return positionToReturn;
    }


}
