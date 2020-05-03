package com.cred.user;


public class User extends Person {

  AccountStatus accountStatus;
  UserType userType;

  public User(String name, Address address, String email, String phone, Gender gender, int age, AccountStatus accountStatus, UserType userType) {
    super(name, address, email, phone, gender, age);
    this.accountStatus = accountStatus;
    this.userType = userType;
  }

  public String getName() {
    return this.name;
  }

  public String getGender() {
    return gender.toString();
  }

  public int getAge() {
    return age;
  }

  public String getAddress() {
    return address.toString();
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getHouseNumber() {
    return address.getHouseNumber();
  }

  public String getStreet() {
    return address.getStreet();
  }

  public String getCity() {
    return address.getCity();
  }

  public String getState() {
    return address.getState();
  }

  public String getCountry() {
    return address.getCountry();
  }

  public String getPinCode() {
    return address.getPinCode();
  }

  public AccountStatus getAccountStatus() {
    return accountStatus;
  }

  public UserType getUserType() {
    return userType;
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("User = {\n").append(super.toString()).append("\n");
    str.append("AccountStatus = ").append(accountStatus.toString()).append("\n");
    str.append("UserType = ").append(userType.toString()).append("\n");
    str.append("}");
    return str.toString();
  }
}
