import java.util.ArrayList;
import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

//Records:
//Record classes are special classes that act as transparent carriers for immutable data.
//They are immutable classes (all fields are final) and are implicitly final classes, which means they can't be extended.
record Customer(String name, ArrayList<Double> transactions){
    Customer(String name, ArrayList<Double> transactions) {
        this.name = name;
        this.transactions = transactions;
    }

    Customer(String name, double initialDeposit){
        this(name.toUpperCase(),
            new ArrayList<Double>(500));
        transactions.add(initialDeposit);
    }
}
public class Main {

    public static void main(String[] args) {


        Integer autoBoxed = 15;
        int autoUnboxed = autoBoxed;

        System.out.println(autoBoxed.getClass().getName());
        System.out.println(autoUnboxed);
        System.out.println("-".repeat(20));

        // the unboxing occurs automaticly
        Double getResultBox = getLiteralDoublePrimitive(); // the method returns primitive double but its unboxed automaticly to Double because of hte  left side
        double resultUnboxed = getDoubleObject();


        Integer[] wrapperArray = new Integer[5];
        wrapperArray[0]=50;
        System.out.println(Arrays.toString(wrapperArray));
        System.out.println(wrapperArray[0].getClass().getName());
        System.out.println("-".repeat(20));


        Character[] characterArray = {'a','b','c','d'};
        System.out.println(Arrays.toString(characterArray));
        System.out.println(characterArray[0].getClass().getName());
        System.out.println("-".repeat(20));

        var IntegerList = getList(1,2,3,4,5);  //the same as List.of
        System.out.println(IntegerList.get(1).getClass());
        System.out.println(IntegerList);
        System.out.println("-".repeat(20));

      ////////// Challange Main //////////
        /*In this challenge, you will need to create a simple banking application, with a Customer and Bank type.
        The Customer will have a name, and an ArrayList of transactions containing Double wrapper elements.
        A customer's transaction can be a credit, which means a positive amount, or it can be a debit, a negative amount.

        The Bank will have a name, and an ArrayList of customers.
       - The bank should add a new customer, if they're not yet already in the list.
       - The bank class should allow a customer to add a transaction, to an existing Customer.
       - This class should also print a statement, that includes the customer name, and the transaction amounts.  This method should use unboxing.
       */
        //Main Code//
        System.out.println("-----------------Challange-----------------");
        Customer bob = new Customer("Bob D",1000.0);
        Customer Dylan = new Customer("Dylan B",20);
        System.out.println(bob);
        //System.out.println(bob.name());
        //System.out.println(bob.transactions());
        System.out.println("-".repeat(20));

        Bank bank = new Bank("Ziraat");
        bank.AddNewCustomer("Zoe B",500);
        bank.AddNewCustomer("Dylan B",100);
        System.out.println(bank);
        bank.AddTransaction("Zoe B",1000);
        bank.AddTransaction("Zoe B",-10.25);
        bank.AddTransaction("Zoe B",-300);
        System.out.println(bank);

        bank.PrintStatement("Zoe b");


    }

    private static Double getDoubleObject(){
        return Double.valueOf(100.00);
    }

    private static double getLiteralDoublePrimitive(){
        return  100.00;
    }

    //converts the Integer type parameter to int
    private static int returnAnInt(Integer i){
        return i;
    }

    //converts the int type parameter to Integer
    private static Integer returnAnInt(int i){
        return i;
    }

    private static ArrayList<Integer> getList(int... arg){
        ArrayList<Integer> aList = new ArrayList<>();
        for(int i : arg){
            aList.add(i);
        }
        return aList;
    }

}

////////// Challange Classes //////////
class Bank {
    private String name;
    private ArrayList<Customer> customers = new ArrayList<>(5000);

    public Bank(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", customers=" + customers +
                '}';
    }

    private Customer getCustomer(String customerName) {
        for (var customer : customers) {
            if (customer.name().equalsIgnoreCase(customerName)) {
                return customer;
            }
        }
        System.out.println("Customer was not Found");
        return null;
    }

    public void AddNewCustomer(String customerName, double initialDeposit) {
        if (getCustomer(customerName) == null) {
            Customer customer = new Customer(customerName, initialDeposit);
            customers.add(customer);
            System.out.println("New Customer added: " + customer);
        }
    }

    public void AddTransaction(String name,double TransactionAmount){


        Customer customer = getCustomer(name);
        if(customer != null){
            customer.transactions().add(TransactionAmount);
        }
    }

    public void PrintStatement(String customerName){
        Customer customer = getCustomer(customerName);
        if(customer== null){
            return;
        }
        System.out.println("-".repeat(30));
        System.out.println("Customer Name: " + customer.name());
        System.out.println("Transactions");
        for(double d : customer.transactions()){ // unboxing
            System.out.printf("$%10.2f (%s)%n", d ,(d<0) ? "debit":"credit");

        }

    }
}