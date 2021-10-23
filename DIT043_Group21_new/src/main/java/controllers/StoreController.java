package controllers;

import primitives.Employee;
import primitives.Item;
import primitives.Review;
import primitives.Transaction;

public class StoreController {
    ArrayList<ItemTransactionIndex> profitList;
    public Item item;
    public Review review;


    public StoreController(){
        items = new ArrayList<Item>();
        transactions = new ArrayList<Transaction>();
        profitList = new ArrayList<ItemTransactionIndex>();
    }

    public boolean itemExistenceChecker(String ID){
        for (Item item : items) {
            if (item.getId().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public boolean itemExists(String ID){
        for(int i = 0 ; i< items.size(); i++){
            Item n = items.get(i);
            if(n.getId().equals(ID)){
                return true;
            }
        }
        return false;
    }

    public double totalPurchases(){
        double total = 0;
        for(Transaction a : transactions){
            total = total + a.getPrice();
        }

        return total;
    }

    public int totalUnits(){
        int units = 0;
        for(int i = 0; i < transactions.size();i++){
            units += transactions.get(i).getAmount();
        }
        return units;
    }

    public int totalTransaction(){
        return transactions.size();

    }

    public double getSpecificItemProfit(String ID){
        double profit = 0;
        if(itemExists(ID)){
            for(int i = 0; i < transactions.size();i++){
                if(ID.equals(transactions.get(i).getId())) {
                    profit += transactions.get(i).getPrice();
                }
            }
            return profit;
        }
        return profit;
    }
    public int getSpecificItemAmmount(String ID){
        int unitSold = 0;
        if(itemExists(ID)){
            for(int i = 0; i < transactions.size();i++){
                if(ID.equals(transactions.get(i).getId())) {
                    unitSold += transactions.get(i).getAmount();
                }
            }
            return unitSold;
        }
        return unitSold;

    }
    public int getSpecificTransactions(String ID){
        int transactionCount = 0;
        if(itemExists(ID)){
            for(int i = 0; i < transactions.size();i++){
                if(ID.equals(transactions.get(i).getId())) {
                    transactionCount++;
                }
            }
            return transactionCount;
        }
        return transactionCount;

    }






    public String getAllItemTransactions(String ID){
        if(itemExists(ID) == false) {
            return ("Item "+ ID +" was not registered yet.");
        }
        Item specificItem = getItemById(ID);
        String result = ("Transactions for item: " + specificItem.getId() + ": " + specificItem.getName() +". " + specificItem.getPrice() + " SEK\n");
        int transactionCount = 0;
        for(int i = 0 ; i < transactions.size();i++){
            if(ID == transactions.get(i).getId()){
                result +=  transactions.get(i).toString() + "\n";
                transactionCount++;
            }
        }
        if(transactionCount == 0){
            DecimalFormat df = new DecimalFormat("0.00");

            return "Transactions for item: "+ specificItem.getId() +": "+specificItem.getName() + ". "+ df.format(specificItem.getPrice()) + " SEK\n" +
                    "No transactions have been registered for item "+ specificItem.getId()+" yet.";
        }
        return result;




    }
    public Item getItemById(String id){
        for(int i =0  ; i < items.size() ; i++){
            if(items.get(i).getId() == id){
                return items.get(i);
            }
        }
        return null;
    }

    public String createValidItem (String ID, String name , double price){
        if(price < 0 || ID.isEmpty() || name.isEmpty()) {
            return "Invalid data for item.";
        }
        //double newPrice = MathHelpers.truncateDouble(price);
        Item item = new Item (ID, name, price);
        items.add(item);
        return ("Item " + ID + " was registered successfully.");
    }

    public String removeValidItem (String inputID){

        Item inputItem = getItemById(inputID);
        boolean checker = items.remove(inputItem);
        if (checker) {
            return ("Item " + inputItem.getId() + " was successfully removed.");
        }
        else{
            return ("Item " + inputID + " could not be removed.");
        }

    }
    public String updateItemPrice( String ID  ,double newPrice) {
        DecimalFormat df = new DecimalFormat("0.00");
        if(itemExistenceChecker(ID) != true){
            return  "Item " + ID + " was not registered yet.";
        }
        else if (ID.isEmpty() ||newPrice <= 0) {
            return "Invalid data for item.";
        }
        else {
            this.item.setPrice(newPrice);
            return  "Item "+ this.item.getId()+ " was updated successfully.";
        }
    }

    public String printAllTransactions(){
        DecimalFormat df = new DecimalFormat("0.00");
        String result = "All purchases made: " + "\n" + "Total profit: " + df.format(totalPurchases()) + " SEK\n" + "Total items sold: "+ totalUnits()+" units\n" + "Total purchases made: " + totalTransaction()+  " transactions\n"+ "------------------------------------\n";
        if(transactions.isEmpty() == false){
            for(Transaction n : transactions){
                result += n.toString()+ "\n";
            }
            return  result + "------------------------------------\n";
        }else{
            return  result + "------------------------------------\n";
        }

    }


    public  void addAllItemsProffit(){
        if(items.isEmpty() == false && transactions.isEmpty() == false){
            for(Item n : items){
                double nProfit = getSpecificItemProfit(n.getId());
                ItemTransactionIndex foo = new ItemTransactionIndex(n.getId() , nProfit);
                profitList.add(foo);
            }
        }
    }

    public String printMostProfitable(){
        DecimalFormat df = new DecimalFormat("0.00");
        addAllItemsProffit();

        ItemTransactionIndex min = profitList.get(0);
        ItemTransactionIndex max = profitList.get(0);
        int size = profitList.size();

        for(int i = 1;  i < size; i++){
            if(profitList.get(i).profit > max.profit){
                max = profitList.get(i);
            }
        }

        Item MostProfitable = getItemById(max.getID());
        return "Most profitable items: \n" + "Total profit: "+ df.format(max.profit) +" SEK\n" + MostProfitable.toString()+"\n";




    }
    public String updateNameItem( String ID  ,String newName) {
        if(itemExistenceChecker(ID) != true){
            return  "Item " + ID + " was not registered yet.";
        }
       else if (ID.isEmpty() ||newName.isEmpty()) {
            return "Invalid data for item.";
        }
        else {
            Item inputItem = getItemById(ID);
            inputItem.setName(newName);
            return  "Item " + ID + " was updated successfully.";
        }
    }

    public String getAllItems(){
            for (Item item : items) {
               System.out.println(item.toString());
            }
            return null;
    }

    public void sortItemsDecendingMeanReview(){
        Item temp ;
        for(int a = 0; a < items.size(); a++)
        {
            for(int b = a + 1; b < items.size(); b++)
            {
                if(items.get(a).getMeanReview() > items.get(b).getMeanReview())
                {
                    temp = items.get(a);
                    items.set(a,items.get(b));
                    items.set(b , temp);
                }
            }
        }
    }
    public void sortItemsAcendingMeanReview(){
        Item temp ;
        for(int a = 0; a <= items.size() - 1; a++)
        {
            for(int b = 0; b <= items.size() - 2; b++)
            {  if(items.get(a).reviews.isEmpty()){
                items.remove(a);
            }else if(items.get(b).reviews.isEmpty()){
                items.remove(b);
            } else if(items.get(b).getMeanReview() < items.get(b + 1).getMeanReview())
                {
                    temp = items.get(b);
                    int c = b+1;
                    items.set(b,items.get(b+1)) ;
                    items.set((b+1) , temp);
                }
            }
        }
    }


    public void sortItemsDecendingReviewQuantity(){
        Item temp ;
        for(int a = 0; a < items.size(); a++)
        {
            for(int b = a + 1; b < items.size(); b++)
            {
                if(items.get(a).reviews.isEmpty()){
                    items.remove(a);
                }else if(items.get(b).reviews.isEmpty()){
                    items.remove(b);
                } else if(items.get(a).reviews.size() > items.get(b).reviews.size())
                {
                    temp = items.get(a);
                    items.set(a,items.get(b));
                    items.set(b , temp);
                }
            }
        }







    }
    public void sortItemsAcendingReviewQuantity(){
        Item temp ;
        for(int a = 0; a <= items.size() - 1; a++)
        {
            for(int b = 0; b <= items.size() - 2; b++)
            {
                if(items.get(a).reviews.isEmpty()){
                    items.remove(a);
                }else if(items.get(b).reviews.isEmpty()){
                    items.remove(b);
                } else if(items.get(b).reviews.size() < items.get(b + 1).reviews.size())
                {
                    temp = items.get(b);
                    int c = b+1;
                    items.set(b,items.get(b+1)) ;
                    items.set((b+1) , temp);
                }
            }
        }

    }

    public List<String> getItemsWithMostReviews(){
        sortItemsAcendingReviewQuantity();
        List <String> mostReviews = new ArrayList<String>();
        for(Item n : items){
            if(n.reviews != null || n.reviews.size()!=0) {
                mostReviews.add(n.getId());
            }
        }


        return mostReviews;
    }

    public String printMostReviewedItems(){
        List <String> mostReviews = getItemsWithMostReviews();
        String Result = "Most reviews: " + getItemById(mostReviews.get(0)).getNumberOfReviews() + " review(s) each.\n";
        for(String id : mostReviews){
            Result += getItemById(id).toString() + "\n";

        }
        return Result;
    }

    public List<String> getItemsWithLeastReviews(){
        sortItemsDecendingReviewQuantity();
        List <String> leastReviews =  new ArrayList<String>();

        Item n = items.get(0);

        for(int i = 1; i < n.getNumberOfReviews(); i++){
            if(items.get(i).getNumberOfReviews() < n.getNumberOfReviews()){
                n = items.get(i);
            }
        }
        leastReviews.add(n.getId());

       // leastReviews.add(items.get(0).getId());

        return leastReviews;

    }

    public String printLeastReviewedItems(){
        List <String> leastReviews = getItemsWithLeastReviews();
        String Result = "Most reviews: " + getItemById(leastReviews.get(0)).getNumberOfReviews() + " review(s) each.\n";
        for(String id : leastReviews){
            Result += getItemById(id).toString() + "\n";

        }
        return Result;
    }

    public List<String> getItemsWithBestMeanReviews(){
        sortItemsAcendingMeanReview();
        List <String> mostReviews = new ArrayList<String>();
        for(Item n : items){
            if(n.reviews != null || n.reviews.size()!=0) {
                mostReviews.add(n.getId());
            }
        }


        return mostReviews;
    }




    public String printSpecificItem(String ID) {
        if (!itemExistenceChecker(ID)) {
            return("Item " + ID + " was not registered yet.");
        } else {
            Item item = getItemById(ID);
            return(item.getId() + ": " + item.getName() + ". " + item.getPrice() + " SEK");
        }
    }


    // Not finished , it shoudl acomodate for the transactions class
    public double buyItem(String ID , int quantity){
        int discountedQuantity;
        Item BoughtItem = getItemById(ID);
        double total = BoughtItem.getPrice() * quantity;

        if (itemExistenceChecker(ID) == false){
            return  -1.0;
        } else if(quantity <= 4 && quantity > 0){
            double total = BoughtItem.getPrice() * (double) quantity;
            Transaction transaction = new Transaction(ID , quantity ,  total);
            transactions.add(transaction);

            return total;
        }
        else{
            double totalWithoutDiscount = BoughtItem.getPrice() * 4.0;


            double total = MathHelpers.truncateDouble((totalWithoutDiscount + (BoughtItem.getPrice() * (quantity-4)) * (1-0.3)));
            Transaction transaction = new Transaction(ID , quantity ,  total);
            transactions.add(transaction);
            return total ;
        }
        return total;
        // need to add a -1 at the end
    }


    public String getSpecificItemReview(String ID , int index){
        Item n = getItemById(ID);
        return n.getSpecificReview(index);
    }

    public String getAllReviewsItem(String ID){
        Item n = getItemById(ID);
        return n.printAllReviews();
    }

    public String getAllReviews(){
        for(int i = 0; i< items.size();i++){
           System.out.println(items.get(i).printAllReviews());
        }
        return null;

    }


    // Add sorting functions

    // 3.1 - Nia

//method creating reviews

    public String createReviews(String ID , String writtenComment , int grade){
        if(itemExists(ID) != true){
            return "item does not exist";
        } else{
            Item item = getItemById(ID);
            item.createReview(grade , writtenComment);
            return("Your item review was registered successfully.");

        }

    }
//3.2 - Nia
//print a specific item review


    public List<String> getItemComment(String ID){
        return getItemById(ID).printAllWrittenReviews();
    }

    public String printAllReviews(){
        String result = "All registered reviews:\n" + "------------------------------------\n";
        for(Item item: items){
            if(item.reviews.isEmpty() == false) {
                result += item.getAllReviwewsForItem() + "------------------------------------\n";
            }
        }
        return result;
    }

    public String getReviewsForItem(String ID){
        return getItemById(ID).getAllReviwewsForItem();
    }

    public String printSpecificItemReview(String ID,int i) {

        Item item = getItemById(ID);

        if (itemExistenceChecker(ID)) {
            return "Item" + ID + "was not registered yet.";
        }
        else if (item.reviews.isEmpty()) {
            return "Item " + item.getName() + " has not been reviewed yet.";
        }
        else if (i < 1 || i > item.reviews.size()) {
            return "Invalid review  number. Choose between 1 and " + item.reviews.size();
        }
        return item.reviews.get(i).reviewToString();
    }


    //3.4 - Oscar

    public double getMeanItemGrade(String id){
        return getItemById(id).getMeanReview();
    }

    public int getItemReviewById(String ID){
        return getItemById(ID).getNumberOfReviews();
    }

    public String retrieveMeanGradeItem(String ID) {
        if (itemExistenceChecker(ID)){
            return ("Item " + ID + " was not registered yet.");
        }

        else if(item.reviews.isEmpty()) {
            Item inputItem = getItemById(ID);
            return ("Item " + inputItem.getName() + " has not been reviewed yet");
        }

        else {
            Item inputItem = getItemById(ID);
            double totalGrades = 0;
            for (int i = 0; i < item.reviews.size(); i++) {
                totalGrades += inputItem.reviews.get(i).getGrade();
            }
            double mean = (totalGrades/item.reviews.size())*Math.pow(10,1); //math pow useless here
            return(String.valueOf(mean));
        }

    }

    //3.5 - Oscar
    public String retrieveCommentsItem(String ID){

        Item inputItem = getItemById(ID);

        if (itemExistenceChecker(ID) || item.reviews.isEmpty()) {
            return "Empty Collection";
        }

        else {
            String result = "";
            for (int i = 0; i < item.reviews.size(); i++) {
                result += item.reviews.get(i).getWrittenReview() + "\n";
            }

            return result;
        }
    }

//3.6 - Oscar

    public String retrieveRegisteredReviews() {
        int noReviewedItem = 0;
        String result = "";

        if (items.size() == 0) {
            return "No items registered yet";
        }

        else {

            for (Item item : items) {
                if (item.reviews.size() == 0) {
                    noReviewedItem++;
                }
            }

            if (noReviewedItem == items.size()) {
                return ("No items were reviewed yet");
            } else {
                for (Item item : items) {
                    result = "--------------------------\n" + "Review(s) for " + item.getId() + ": " + item.getName() + ". " + item.getPrice() + " SEK + \n";

                    for (int j = 0; j < item.reviews.size(); j++) {
                        result += "Grade: " + item.reviews.get(j).getGrade() + "." + item.reviews.get(j).getWrittenReview();
                    }
                }
            }
        }

        return "All registered reviews:\n" + result;
    }


    public String getSpecificReviewFromItem(String ID , int reviewNumbrer){
        return getItemById(ID).getSpecificReview(reviewNumbrer);
    }

    public String printAllItems() {
        if (items.isEmpty()) {
            return "No items registered yet.";
        } else {


            String message = "All registered items:\n";
            for (Item item : items) {
                message += item.toString() + "\n";
            }
            return message;
        }
    }
}




