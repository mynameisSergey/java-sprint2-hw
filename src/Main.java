import java.util.Scanner;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        System.out.println("Введите команду");
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport(2021, "resources/y.2021.csv");

        ArrayList<MonthlyReport> oneMonth = new ArrayList<>();

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {

                for (int m = 1; m <= 3; m++) {
                    MonthlyReport monthlyReport = new MonthlyReport();
                    monthlyReport.oneMonthReport("resources/m.20210" + m + ".csv");
                    if (monthlyReport == null) {
                        System.out.println("Произошда ошибка. Такого отчета не существует");
                    } else {

                        oneMonth.add(monthlyReport);
                    }
                }
                    System.out.println("Отчеты по каждому месяцу считаны");

            }
            if (command == 2) {
                yearlyReport = new YearlyReport(2021, "resources/y.2021.csv");
            }
            if (command == 3) {
                if ((oneMonth != null) && (yearlyReport != null)) {
                    for (int i = 0; i < 3; i++) {
                        int incomeMonth = oneMonth.get(i).monthIncome();  // расчет дохода по месяцу
                        int expenseMonth = oneMonth.get(i).monthExpense(); // расчет расходов по месяцу

                        int incomeYear = yearlyReport.sumIncome(i + 1); // подсчет дохода за месяц в году
                        int expenseYear = yearlyReport.sumExpense(i + 1); // подсчет расходов за месяц в году

                        if (incomeMonth != incomeYear && expenseMonth != expenseYear) {
                            System.out.println("В " + (i + 1) + " месяце допущена ошибка");
                        }

                    }
                    System.out.println("Сверка отчетов завершена успешно");
                }
            }
                if (command == 4) {
                    for(int i = 0; i < 3; i++) {

                        System.out.println("Данные за " + (i+1) + " месяц:");
                        System.out.println("Самая высокая прибыль - " + oneMonth.get(i).maxProfit() + " руб.");
                      System.out.println("Самая большая трата - " + oneMonth.get(i).maxSpending() + " руб.");
                    }
                }
                if (command == 5) {
                    System.out.println("Информация об отчете за 2021 год");
                    yearlyReport.incomeYear();
                    yearlyReport.expenseYear();
                    yearlyReport.monthProfit();
                } else {
                    if (command == 0){
                        System.out.println("До новых встречь!");
                        break;
                    }
                }
            }
        }


    public static void printMenu() {
        System.out.println("Выберите действие, которое необходимо выполнить:");
        System.out.println("1 - Считать все месячные отчеты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Сверить отчеты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчете");
        }
}





