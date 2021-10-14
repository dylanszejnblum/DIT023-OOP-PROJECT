package clothingstore;

import java.util.ArrayList;

public class Item {
 
    public  String name;
    public  double price;
    public  String  ID;
    public ArrayList <Integer> grades;
    public ArrayList <String> writtenComments;
    public ArrayList<Transaction> transactionList;
    public ArrayList<String> transactionStringArray;


    public Item(String  ID,String name, double price) {
        this.name = name;
        this.price = price;
        this.ID = ID;
        /*
        if(price < 0 || ID.isEmpty() || name != "") {
            throw new IllegalArgumentException("Invalid data for item.");
        }
        */

        this.grades = new  ArrayList<Integer>();
        this.writtenComments =  new ArrayList<String>();

    }

    public String getId() {
        return this.ID;
    }

    public double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }
    // A function to update the Item Name and price
    public int getReviewLength(){
        return this.writtenComments.size();
    }

    public int getMeanGrade(){
        int total = 0;
        int average = 0;
        for(int i = 0 ; i < grades.size(); i++){
            total += grades.get(i);
            average = total / grades.size();
            return average;

        }
        return average;

    }
    public void updatePriceItem(double Price) {
        this.price = Price;

    }

    public void updateNameItem(String Name) {
        this.name = Name;
    }

    public boolean equals(Item anotherItem){
        if(anotherItem == this){
            return true;

        } else if(anotherItem == null){
            return false;

        } else if ( anotherItem instanceof Item ){
            boolean isEqualItem = (this.getId() ==  anotherItem.getId()) ;
            return isEqualItem;

        } else {
            return false;
        }
    }


}

//1- Check equality of ID, create a method for that --> done.
//2- Truncation
//3- do while loops for menu
//4- Review class
//5- FACADE
//6- Create a method for checking if the ID exists or not. --> done.
//7- Encapsulation, we currently have no encapsulation --> done.
//8- Create toString methods in order to print, currently we have none
//9- Naming conventions according to Java