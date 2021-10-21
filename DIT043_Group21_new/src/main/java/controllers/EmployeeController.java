package controllers;
import primitives.*;
import java.util.ArrayList;
import helpers.*;
import primitives.Employee;

public class EmployeeController {

    public Employee employee;
    public ArrayList<Employee> employees;


    //method for retrieving an Employee by its ID.\\

    public Employee getEmployeeById(String ID){

        for(int i = 0; i < employees.size() ; i++){
            if(employees.get(i).getID().equals(ID)){
                return employees.get(i);
            } }
        return null; }


    public EmployeeController(){
        ArrayList<Employee> employees = new ArrayList<Employee>();
    }

    public boolean equals(Employee anotherEmployee){
        if(anotherEmployee == this.employee){
            return true;

        } else if(anotherEmployee == null){
            return false;

        } else if ( anotherEmployee instanceof Employee ){
            return (this.employee.getID().equals(anotherEmployee.getID()));

        } else {
            return false;
        }
    }

    

    public String toString() {
        return this.employee.getName() + "'s gross salary is " + this.employee.getInitialGrossSalary() + " SEK per month";
    }

    public double calculateNetSalary() {
        double finalNetSalary = 0.0;
        finalNetSalary= employee.getNetSalary() - (this.employee.getInitialGrossSalary() * employee.getTAX_PERCENTAGE());
        return MathHelpers.truncateDouble(finalNetSalary);
        // Add the truncate function into the helpers
    }


              //------CREATE METHODS FOR EVERY KIND OF EMPLOYEES------\\


    public String createEmployee (String ID, String name, double initialGrossSalary) {
        Employee employee = new Employee(ID, name, initialGrossSalary);
        employees.add(employee);
        return "Employee " + this.employee.getID() + " registered successfully.";
    }


    public String createManager(String ID, String name, double initialGrossSalary, String degree){
        Employee managerEmployee = new Manager(ID, name ,initialGrossSalary, degree);
        employees.add(managerEmployee);
         return "Employee " + managerEmployee.getID() + " registered successfully.";
    }


    public String createDirector(String ID, String name, double initialGrossSalary,String degree, String department){
        Employee directorEmployee = new Director(ID, name, initialGrossSalary, degree, department);
        employees.add(directorEmployee);
      return "Employee " + directorEmployee.getID() + " registered successfully.";
    }


    public String createIntern(String ID, String name, double initialGrossSalary,int gpa){
        Employee internEmployee = new Intern(ID, name, initialGrossSalary, gpa);
        employees.add(internEmployee);
        return "Employee " + internEmployee.getID() + " registered successfully.";
    }

         // --------------------------------------------------------------------------\\

     //removing stored employee 5.4\\
    public String removeStoredEmployees(String ID) {
        for (int i = 0; i < employees.size(); i++) {

            if (ID.equals(employees.get(i).getID())) {
                employees.remove(employees.get(i));

                return "Employee " + ID + " was successfully removed.";
            }
          } return "";}  //idk what to return if it's not removed successfully.}



    //printing specific employee - 5.5\\
    public String printSpecificEmployee(String ID){

        if(checker(ID)) {
            return getEmployeeById(ID).toString();
        }
        return "No employee with such an ID"; //not sure what should be printed in this case.
        }




        //checker if an employee with such an ID exists\\
        public boolean checker(String ID){

        for(int i = 0; i<employees.size(); i++){
            if(ID.equals(employees.get(i).getID())){return true;}
        }
        return false;
        }


        //printing all employees - 5.6\\

        public String printAllEmployees(){
        String result = "";
        for (int i = 0; i < employees.size(); i++){
            result += employees.get(i).toString() + "\n";
        }
        return "All registered employees: \n" + result;
        }


        //print total salary expenses - 5.7\\

        public double printTotalSalaryExpenses(){
         double total = 0.0;

         for(int i = 0; i < employees.size(); i++){
             total += employees.get(i).getNetSalary();
         }
        return total;
        }


        //print employees sorted by gross salary - 5.8\\
        public String printEmployeesSortedByGrossSalary(){return "";}


       //






    }









