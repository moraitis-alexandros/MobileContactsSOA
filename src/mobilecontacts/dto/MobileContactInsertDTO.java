package mobilecontacts.dto;

public class MobileContactInsertDTO {
    private  String firstname;
    private String lastname;
    private String phoneNumber;

    public MobileContactInsertDTO(){

    }

    public MobileContactInsertDTO(String phoneNumber, String lastname, String firstname) {
        this.phoneNumber = phoneNumber;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
