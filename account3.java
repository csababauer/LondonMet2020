/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bankaccount3;
/**
 *
 * @author csaba
 */
public class account3 {
    
    
    public String name;
    public int accountNumber;
    public String address;
    public String date;
    public double balance;
    
    
    //I create a new account object
    public account3(int iAccountNumber, String iName, String iAddress, String iDate, double iBalance ){
        accountNumber = iAccountNumber;
        name = iName;
        address = iAddress;
        date = iDate;
        balance = iBalance;     
    }
    
    public String getName(){
        return name;
    }  
    public int getAccountNUmber(){
        return accountNumber;
    }  
    public void setName (String iName){
        name = iName;
    }
    public void setFund (double iBalance){
        balance = iBalance;
    }
    public double getFund(){
        return balance;
    }
    
    
}
