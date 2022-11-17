public class MonthData {
    public String itemName;
    public int quantity;
    public  int sumOfOne;
    public boolean isExpense;
    public int income;
    public int expense;



    public MonthData(String itemName, int quantity, int sumOfOne, boolean isExpense){
        this.itemName = itemName;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
        this.isExpense = isExpense;
    }
}
