import java.util.*;

abstract class Account{
    protected String accountId;
    protected double balance;
    protected String accountType;
    Account(String accountId,double balance,String accountType){
        this.accountId = accountId;
        this.balance = balance;
        this.accountType = accountType;
    }
    void addBalance(int amount){
        balance += amount;
    }
    String getId(){
        return accountId;
    }
    double getBalance(){
        return balance;
    }
    String getType(){
        return accountType;
    }
    void getdetails(){
        System.out.println("AccountID:"+accountId);
        System.out.println("Current Balance:"+balance);
        System.out.println("Account Type:"+accountType);
    }

    void withdrawamount(int amount){}
}
class SavingAccount extends Account{
    SavingAccount(String accountId,double balance){
        super(accountId,balance,"Saving");
    }
    void withdrawamount(int amount){
        if (balance<amount){
            System.out.println("Insufficient balance");
        } 
        else
        {
            balance-=amount;
        }
    }
}
class CreditAccount extends Account{
    final int Creditlimit = 5000;
    private ArrayList<Integer>bills= new ArrayList<>();
    CreditAccount(String accountId,double balance){
        super(accountId,balance,"Credit");
    }
    void withdrawamount(int amount){
        if (balance<amount) {
            System.out.println("Insufficient balance");
        }
        else if(amount>=Creditlimit){
            System.out.println("Creditlimit is 5000");
        }
        else {
            balance-=amount;
            bills.add(amount);
            for(Integer amounts:bills){
                System.out.println(amounts);
            }
        }
    }
}
class BankManagement{
    private ArrayList<Account>accountDetails =new ArrayList<>();
    public void addAccount(Account acc){
        accountDetails.add(acc);
    }
    public void deposit(String accountId,String type,int amount){
        for (Account acc:accountDetails) {
            if (acc.getId().equalsIgnoreCase(accountId)&&acc.getType().equalsIgnoreCase(type)){
                acc.addBalance(amount);
                System.out.println("Amount deposited successfully");
                return;
            }
        }
        System.out.println("Account not found");
    }
    public void withdraw(String accountId,String type,int amount){
        for (Account acc:accountDetails) {
            if (acc.getId().equalsIgnoreCase(accountId)&&acc.getType().equalsIgnoreCase(type)){
                acc.withdrawamount(amount);
                System.out.println("Amount withdrawn successfully");
                return;
            }
        }
        System.out.println("Account not found");
    }
    public void view(String accountId, String type) {
        for (Account acc:accountDetails) {
            if (acc.getId().equalsIgnoreCase(accountId)&&acc.getType().equalsIgnoreCase(type)){
                acc.getdetails();
                return;
            }
        }
        System.out.println("Account not found");
    }
}
public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        BankManagement bank=new BankManagement();
        while (true) {
            System.out.println("\n1: Add Account");
            System.out.println("2:Deposit amount");
            System.out.println("3:Withdraw amount");
            System.out.println("4:View details");
            System.out.println("5:Exit");
            System.out.print("Enter your choice:");

            int choice = sc.nextInt();
            sc.nextLine();

            String accountId,accountType;
            switch (choice){
                case 1:
                    System.out.print("Enter the Account ID: ");
                    accountId = sc.nextLine();
                    System.out.print("Enter the Account Type: ");
                    accountType = sc.nextLine();
                    if(accountType.equalsIgnoreCase("Saving")){
                        bank.addAccount(new SavingAccount(accountId,0));
                    } 
                    else if(accountType.equalsIgnoreCase("Credit")){
                        bank.addAccount(new CreditAccount(accountId,10000));
                    } 
                    else{
                        System.out.println("Invalid Account Type");
                    }
                    break;
                case 2:
                    System.out.print("Enter the Account ID:");
                    accountId=sc.nextLine();
                    System.out.print("Enter the Account Type:");
                    accountType=sc.nextLine();
                    System.out.print("Enter the Deposit Amount:");
                    int depositAmount=sc.nextInt();
                    bank.deposit(accountId,accountType,depositAmount);
                    break;
                case 3:
                    System.out.print("Enter the Account ID:");
                    accountId=sc.nextLine();
                    System.out.print("Enter the Account Type:");
                    accountType=sc.nextLine();
                    System.out.print("Enter the Withdrawal Amount:");
                    int withdrawAmount=sc.nextInt();
                    bank.withdraw(accountId,accountType,withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter the AccountID:");
                    accountId=sc.nextLine();
                    System.out.print("Enter the AccountType:");
                    accountType=sc.nextLine();
                    bank.view(accountId, accountType);
                    break;
                case 5:
                    System.out.println("End the program");
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}
