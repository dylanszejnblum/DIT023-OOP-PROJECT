package facade;

import java.util.List;
import java.util.Map;
import clothingstore.*;
import clothingstore.employee.Employee;

public class Facade {

    Items items = new Items(); //instantiate the object
    public Item item;
    public Review review;
    public Employee employee;

    // This class only has the skeleton of the methods used by the test.
    // You must fill in this class with your own code. You can (and should) create more classes
    // that implement the functionalities listed in the Facade and in the Test Cases.

    public Facade(){

    }

    public String createItem(String itemID, String itemName, double unitPrice){
        //instantiate the item object
        return  items.createItem(itemName, unitPrice, itemID);
    }
    public String printItem(String itemID) {
        return items.printSpecificItem(itemID);
    }

    public String removeItem(String itemID) {
        return items.removeItem(itemID);
    }

    public boolean containsItem(String itemID) {
        return items.itemExistenceChecker(itemID);
    }

    public double buyItem(String itemID, int amount) {
        return items.buyItems(itemID, amount);
    }

    public String reviewItem(String itemID, String reviewComment, int reviewGrade) {

        return review.createReview( itemID,reviewGrade, reviewComment);
    }

    public String reviewItem(String itemID, int reviewGrade) {
        return "";
    }

    public String getItemCommentsPrinted(String itemID) {
        return "";
    }

    public List<String> getItemComments(String itemID) {
        return null;
    }

    public double getItemMeanGrade(String itemID) {
        return -1.0;
    }

    public int getNumberOfReviews(String itemID) {
        return -1;
    }

    public String getPrintedItemReview(String itemID, int reviewNumber) {
        return "";
    }

    public String getPrintedReviews(String itemID) {
        return "";
    }

    public String printMostReviewedItems() {
        return "";
    }

    public List<String> getMostReviewedItems() {
        return null;
    }

    public List<String> getLeastReviewedItems() {
        return null;
    }

    public String printLeastReviewedItems() {
        return "";
    }

    public double getTotalProfit() {
        return -1.0;
    }

    public String printItemTransactions(String itemID) {
        return "";
    }

    public int getTotalUnitsSold() {
        return -1;
    }

    public int getTotalTransactions() {
        return -1;
    }

    public double getProfit(String itemID) {
        return -1.0;
    }

    public int getUnitsSolds(String itemID) {
        return -1;
    }

    public String printAllTransactions() {
        return "";
    }

    public String printWorseReviewedItems() {
        return "";
    }

    public String printBestReviewedItems() {
        return "";
    }

    public List<String> getWorseReviewedItems() {
        return null;
    }

    public List<String> getBestReviewedItems() {
        return null;
    }

    public String printAllReviews() {
        return "";
    }

    public String updateItemName(String itemID, String newName) {
        //return item.updateNameItem(itemID, newName);
        return items.getItemByID(itemID).updateNameItem(newName);
        //Item
        //Items
    }

    public String updateItemPrice(String itemID, double newPrice) {

        return items.getItemByID(itemID).updatePriceItem(newPrice);
    }

    public String printAllItems() {
        return items.printAllItems();
    }

    public String printMostProfitableItems() {
        return "";
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception {
        return employee.createEmployee(employeeID, employeeName, grossSalary);
    }

    public String printEmployee(String employeeID) throws Exception {
        return "";
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception {
        return "";
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception {
        return "";
    }

    public double getNetSalary(String employeeID) throws Exception {
        return -1.0;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String dept) throws Exception {
        return "";
    }

    public String removeEmployee(String empID) throws Exception {
        return "";
    }

    public String printAllEmployees() throws Exception {
        return "";
    }

    public double getTotalNetSalary() throws Exception {
        return -1.0;
    }

    public String printSortedEmployees() throws Exception {
        return "";
    }

    public String updateEmployeeName(String empID, String newName) throws Exception {
        return "";
    }

    public String updateInternGPA(String empID, int newGPA) throws Exception {
        return "";
    }

    public String updateManagerDegree(String empID, String newDegree) throws Exception {
        return "";
    }

    public String updateDirectorDept(String empID, String newDepartment) throws Exception {
        return "";
    }

    public String updateGrossSalary(String empID, double newSalary) throws Exception {
        return "";
    }

    public Map<String, Integer> mapEachDegree() throws Exception {
        return null;
    }

    public String promoteToManager(String empID, String degree) throws Exception {
        return "";

    }

    public String promoteToDirector(String empID, String degree, String department) throws Exception {
        return "";
    }

    public String promoteToIntern(String empID, int gpa) throws Exception {
        return "";
    }
}