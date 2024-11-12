import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<YearlyReport> yearlyReport = new ArrayList<>();
        ArrayList<MonthlyReport> monthlyReport = new ArrayList<>();
        int command;

        do {
            printMenu();
            command = scanner.nextInt();

            switch (command) {
                case 1:
                    for (int m = 1; m <= 3; m++) {
                        String monthPath = "resources/m.20210" + m + ".csv";
                        MonthlyReport monthData = new MonthlyReport(m - 1);
                        monthData.oneMonthReport(monthPath);
                        monthlyReport.add(monthData);
                        System.out.println("Отчет за месяц " + m + " считан");
                    }
                    break;
                case 2:
                    String path = "resources/y.2021.csv";
                    YearlyReport report = new YearlyReport(2021);
                    report.yearReport(path);
                    yearlyReport.add(report);
                    System.out.printf("Отчёт за %d год считан.%n", 2021);
                    break;
                case 3 :
                if (monthlyReport.size() != 3) {
                    System.out.println("Считайте ежемесячные отчеты.");
                } else if (yearlyReport.size() < 1 || yearlyReport.get(0).monthsData.isEmpty()) {
                    System.out.println("Считайте годовой отчёт.");
                } else {

                    boolean hasMistake = true;
                    for (int i = 0; i < 3; i++) {

                        boolean checkReportIncome = (Objects.equals(monthlyReport.get(i).monthIncome(), yearlyReport.get(0).sumProfitMonthOfYear(i + 1)));
                        boolean checkReportExpense = (Objects.equals(monthlyReport.get(i).monthExpense(), yearlyReport.get(0).sumExpensesMonthOfYear(i + 1)));

                        if (!checkReportIncome || !checkReportExpense) {
                            hasMistake = false;
                            System.out.println("В " + (i + 1) + " месяце допущена ошибка");
                        } else {
                            System.out.println("Данные за " + (i + 1) + " месяц сверены успешно");
                        }
                    }
                    if (hasMistake) {
                        System.out.println("Сверка отчетов завершена успешно");
                    } else {
                        System.out.println("Загружены не все отчеты. Повторите попытку снова.");
                    }
                }
                    break;
                case 4 :
                if (monthlyReport.size() != 3) {
                    System.out.println("Считайте ежемесячные отчеты.");
                } else if (yearlyReport.size() < 1 || yearlyReport.get(0).monthsData.isEmpty()) {
                    System.out.println("Считайте годовой отчёт.");
                } else {
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Данные за " + (i + 1) + " месяц:");
                        monthlyReport.get(i).maxProfit();
                        monthlyReport.get(i).maxExpense();
                    }
                }
                    break;

                 case 5 :
                if (yearlyReport.size() < 1 || yearlyReport.get(0).monthsData.isEmpty()) {
                    System.out.println("Считайте годовой отчёт.");
                } else {
                    System.out.println("Информация об отчете за 2021 год");
                    yearlyReport.get(0).differentProfit();
                    yearlyReport.get(0).differentExpenses();
                    yearlyReport.get(0).monthProfit();
                }
                     break;
                default:
                }
        } while (command != 0);
        scanner.close();
    }


        public static void printMenu() {
            System.out.println("Выберите действие, которое необходимо выполнить:");
            System.out.println("1 - Считать все месячные отчеты");
            System.out.println("2 - Считать годовой отчет");
            System.out.println("3 - Сверить отчеты");
            System.out.println("4 - Вывести информацию о всех месячных отчётах");
            System.out.println("5 - Вывести информацию о годовом отчете");
            System.out.println("0 - завершить программу");
        }
    }






