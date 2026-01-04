

//This is our common resource class!
public class Client {


private String businessName;
private int businessNum;
private String clothObject;
private int purchaseAmount;

public Client(String businessName, int businessNum, String clothObject, int purchaseAmount){

    this.businessName = businessName;
    setBusinessNum(businessNum);
    this.clothObject = clothObject;
    setPurchaseAmount(purchaseAmount);

}

//Set methods

public void setBusinessNum(int businessNum) throws IllegalArgumentException{

//Checking if the business number isn't more than 5 numbers or lesser than 5 numbers
    if ((Integer.toString(businessNum).length() > 5 || Integer.toString(businessNum).length() < 5)){

        throw new IllegalArgumentException("Invalid business number.");

    }

    this.businessNum = businessNum;

}


public void setPurchaseAmount(int purchaseAmount) throws IllegalArgumentException{


//Checking that the purchase amount isn't negative.
    if (purchaseAmount <= 0){

        throw  new IllegalArgumentException("Invalid amount of clothes for purchase.");

    }

    this.purchaseAmount = purchaseAmount;

}


//Get methods
public String getBusinessName() {return businessName;}
public int getBusinessNum() {return businessNum;}
public String getClothObject() {return clothObject;}
public int getPurchaseAmount() {return purchaseAmount;}



}
