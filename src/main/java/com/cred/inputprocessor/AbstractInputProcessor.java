package com.cred.inputprocessor;

import com.cred.exceptions.FeatureToggleBaseException;
import com.cred.exceptions.InvalidUserInputException;
import com.cred.user.AccountStatus;
import com.cred.user.Address;
import com.cred.user.Gender;
import com.cred.user.User;
import com.cred.user.UserType;

import java.io.BufferedReader;
import java.io.IOException;

public class AbstractInputProcessor implements Processor {

  private static User user;


  public InputParameters existingUserProcessor() throws IOException , FeatureToggleBaseException{
    user = new User("Tarun Garg",
        new Address("G2", "Outer Ring Road", "Bangalore", "Karnataka", "INDIA", "560101"),
        "tarungarg121@gmail.com", "9090909090",
        Gender.MALE, 62,
        AccountStatus.ACTIVE,UserType.PRIVILEGED);

    System.out.println("User information = " +user.toString());

    String featureName = "Senior Citizen";
    System.out.println("Feature Name to be Tested -> " + featureName);

    String conditionalExpression = "( Age > 60 ) and ( gender == male or test == female )";
    System.out.println("Conditional Expression for Feature -> " + conditionalExpression.toLowerCase());

    return new InputParameters(user,featureName,conditionalExpression.toLowerCase());
  }

  public InputParameters newUserProcessor(BufferedReader reader) throws IOException , FeatureToggleBaseException {

    System.out.println("Create your own user...");

    System.out.println("Enter the name of the user");
    String name = reader.readLine();

    System.out.println("Enter the house number of the user");
    String houseNumber = reader.readLine();

    System.out.println("Enter the street name of the user");
    String streetName = reader.readLine();

    System.out.println("Enter the city of the user");
    String city = reader.readLine();

    System.out.println("Enter the state of the user");
    String state = reader.readLine();

    System.out.println("Enter the country name of the user");
    String country = reader.readLine();

    System.out.println("Enter the pincode of the user");
    String pincode = reader.readLine();

    System.out.println("Enter the email of the used");
    String email = reader.readLine();

    System.out.println("Enter the phone number of the user");
    String phone = reader.readLine();

    System.out.println("Enter the age of the user");
    int age;
    try {
      age = Integer.parseInt(reader.readLine());
    }catch (Exception e){
      System.out.println("Age should be an integer!!");
      System.out.println("Re enter the age of the user");
      age = Integer.parseInt(reader.readLine());
    }

    System.out.println("Enter the gender of the user ( Male/Female)");
    Gender gender;
    String genderString = reader.readLine().toLowerCase();
    try {
      if (genderString.contains("male"))
        gender = Gender.MALE;
      else
        gender = Gender.FEMALE;
    }catch (Exception ex){
      throw new InvalidUserInputException(genderString,"gender");
    }

    System.out.println("Enter the User type of the user (PRIVILEGED/NON_PRIVILEGED)");
    UserType userType;
    String userTypeString = reader.readLine().toLowerCase();
    try {
      if (userTypeString.contains("privileged"))
        userType = UserType.PRIVILEGED;
      else
        userType = UserType.NON_PRIVILEGED;
    }catch(Exception ex) {
      throw new InvalidUserInputException(userTypeString,"userType");
    }

    System.out.println("Enter the account status of the user (ACTIVE/INACTIVE)");
    AccountStatus accountStatus;
    String  accountString = reader.readLine().toLowerCase();;
    try {
      if (accountString.contains("active"))
        accountStatus = AccountStatus.ACTIVE;
      else
        accountStatus = AccountStatus.INACTIVE;
    }catch(Exception ex){
      throw new InvalidUserInputException(accountString,"accountStatus");
    }
    user = new User(name,
        new Address(houseNumber, streetName, city, state, country, pincode),
        email, phone,
        gender, age,
        accountStatus,userType);

    System.out.println("User information = " +user.toString());

    System.out.println("Enter feature name ");
    String featureName = reader.readLine();


    System.out.println("Enter the infix conditional Expression in below format with maintaining space between each word of the expression with brackets \n"
        +"( Operand1  operator1 Operand2 ) operator2 ( Operand3 operator3 Operand4 )\n"
        +"For eg. 1. ( Age > 25 ) and ( gender == male )\n"
        +"2. ( City == mumbai or City == delhi ) and ( UserType == PRIVILEGED )");

    String conditionalExpression  = reader.readLine().toLowerCase();
    return new InputParameters(user,featureName,conditionalExpression);
  }
}
