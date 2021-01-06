/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bankaccount3;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
 
/**
 *
 * @author csaba
 */
public class Bankaccount3 {
    
    static ArrayList<account3>  customer = new ArrayList<account3>();
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    
    public static LinkedList<Double> transactions = new LinkedList<Double> ();
    
    public static int function;
    public static double deposit = 0;
    public static int selectedAccount = 0;
    public static double selectedAmmount =0;
    public static int accountNumber = 3;
           
            
    static void print()
    { 
        //printing all instances from the customer arraylist 
        System.out.println("Current customers: "); 
        for(account3 a:customer){   
        System.out.println(a.accountNumber +" / "+ a.name + " / "+ a.address +" / " + a.date+ " / "+ a.balance); 
        }  
        System.out.println(" " +System.lineSeparator());
        
    }
    
    static void otherService()
    {
        //getting information if the user wants more service, otherwise the program terminates.
        Scanner moreService = new Scanner( System.in );
        System.out.println("Would you like other service? Y/N");
        String nextStep = moreService.nextLine();
        if (nextStep.equals("y")){
            menu();            
        }
        else 
        {
          System.out.println("Good Bye!");
        }
        
        
    }
    
    static void addNew()
    {
        Scanner input = new Scanner( System.in );
        accountNumber = accountNumber + 1;
        System.out.println( "enter new customer name:  " );
        String newCustomer = input.nextLine();
        System.out.println( "enter new customer address:  " );
        String newAddress = input.nextLine();
        System.out.println( "enter new balance:  " );
        double newBalance = input.nextInt();
        
        //get today's date
        Date date = new Date();
        //format date to year/month/day
        String todayDate = sdf.format(date).toString();
        
        //adding new customer to the customer ArrayList
        account3 a4 = new account3(accountNumber, newCustomer, newAddress, todayDate, newBalance);  
        customer.add(a4);
    }
    
    static void transact()
    {
        //adding deposit or withdraw
        Scanner transaction = new Scanner( System.in );
        //get the size of the customer array to find out the max number of existing customer
        int maxCustomer = customer.size() - 1;
        System.out.println( "select an account number for deposit, enter 0-"+maxCustomer);
        int selectedAccount = transaction.nextInt();
        //find customer and change balance
        double oldBalance = customer.get(selectedAccount).getFund();
        transaction.nextLine(); // This line you have to add (It consumes the \n character)
        System.out.println( "do you want to withraw or deposit? w/d" );
        String selectedAction = transaction.nextLine();
        //input.nextLine(); // This line you have to add (It consumes the \n character)
        System.out.println( "how much do you want to withraw or deposit? " );
        selectedAmmount = transaction.nextDouble();
        
        if (selectedAction.equals("d")){
            deposit = oldBalance + selectedAmmount;
        } 
        else if (selectedAction.equals("w")) {
            deposit = oldBalance - selectedAmmount;
        }   
        else {
            System.out.println("not valid input");
        }
        
        customer.get(selectedAccount).setFund(deposit);
        
        //adding amount to the transactions linkedlist
        transactions.addFirst(selectedAmmount);
        
        LinkedList<Double> tr1 = new LinkedList<Double>();
        tr1.addFirst(oldBalance);
        System.out.println("amount:  "+tr1);
                
        //print account with the new balance
        System.out.println(
                "account number: " + customer.get(selectedAccount).getAccountNUmber() + "/"+
                "name: " + customer.get(selectedAccount).getName() + "/"+ 
                "new balance: " + customer.get(selectedAccount).getFund() );
    }
    
    public static void menu()
    {
        System.out.println("Select the function from the menu:  ");
        System.out.println("Display accounts                            - 1  ");
        System.out.println("create a new account and add to the system  - 2  ");
        System.out.println("New transaction of a selected account       - 3  ");
        System.out.println("Find the latest transaction of an account   - 4  ");
        System.out.println("Delete an account                           - 5  ");
        System.out.println(" " +System.lineSeparator());
        System.out.println( "seleect option: 1 / 2 / 3 / 4 / 5 :  " );
        
        Scanner input = new Scanner( System.in );
        function = input.nextInt();
         
        try{
         switch(function) {
         case 1:
         print(); 
         otherService();
         break;
         case 2:
         addNew();
         print();
         otherService();
         break;
         case 3:
         transact();
         otherService();
         break; 
         case 4:
         latestTransactions();
         otherService();
         break;      
         case 5:
         delete();
         print();
         otherService();
         break;          
        }
        }catch (Exception e) {
         System.out.println("Something went wrong. please make sure you selected an existing number"); 
         menu();
        }
         
    }
    
    public static void delete()
    {
        int allCustomers = customer.size()-1; //first customer number is 0, last is arrysize -1.
        Scanner delete = new Scanner( System.in );
        System.out.println( "select an account you want to delete enter 0 - " + allCustomers );
        try{
        int deletedAccount = delete.nextInt();
        customer.remove(deletedAccount);
        } catch (Exception e) {
         System.out.println("Something went wrong. please make sure you selected an existing account number");   
        }
        
    }
    public static void latestTransactions()
    {
        //Scanner selectedCustomer = new Scanner( System.in );
        //get the size of the customer array to find out the max number of existing customer
        int maxCustomer = customer.size() - 1;
        System.out.println( "select an account number you want the latest 6 tranaction from, enter 0-"+maxCustomer);
        //int selectedAccount2 = selectedCustomer.nextInt();
       
        System.out.println("transactions " + transactions);
        System.out.println("last selected ammount: " + selectedAmmount);
              
    }
    
    public static void main(String args[])
    {     
        //polpulating the Arraylist with 3 instances
        account3 a1 = new account3(001, "John Smith", "89 Church Street, London", "7/11/2020", 100 );  
        customer.add(a1);
                       
        account3 a2 = new account3(002, "John Wins", "77 Main Street, London", "6/3/2019", 200); 
        customer.add(a2);     
        
        account3 a3 = new account3(003, "John Goood", "22 West Street, London", "4/4/2018", 300); 
        customer.add(a3);
         
        //print menu options:
        menu(); 
        
        
     }
}


