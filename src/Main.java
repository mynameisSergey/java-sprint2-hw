import java.util.Scanner;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        System.out.println("Введите команду");
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport(2021, "resources/y.2021.csv");
        MonthlyReport monthlyReport = new MonthlyReport();
        ArrayList<MonthlyReport> oneMonth = new ArrayList<>();

        for (int i = 1; i<=3; i++) {
            monthlyReport = new MonthlyReport();
        }

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                for (int m = 1; m <= 3; m++) {
                    monthlyReport.oneMonthReport("resources/m.20210" + m + ".csv");
                    oneMonth.add(monthlyReport);
                }
                System.out.println("Отчеты по каждому месяцу считаны");
            }
            if (command == 2) {
                yearlyReport = new YearlyReport(2021, "resources/y.2021.csv");
            }
            if (command == 3) {
                for (int i = 0 ; i < 3; i++) {
                    int profitMonth;
                    monthlyReport = oneMonth.get(i);
                    profitMonth = monthlyReport.monthProfit();
                    if (yearlyReport.sumProfit(i+1) == profitMonth) {
                        System.out.println("Сверка отчетов завершена успешно");
                    } else {
                        System.out.println("В " + (i+1) + " месяце допущена ошибка");
                    }
                }
            }
            if (command == 4) {
                for(int i = 0; i < 3; i++) {

                    System.out.println("Данные за " + (i+1) + " месяц:");
                    System.out.println("Самая высокая прибыль по товару " + monthlyReport.maxProfit() + " рублей");
                    System.out.println("Самая большая трата по товару " + monthlyReport.maxSpending() + " рублей");
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





