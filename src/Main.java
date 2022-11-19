import java.util.Scanner;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        System.out.println("Введите команду");
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = null;
        MonthlyReport monthlyReport = null;
        ArrayList<MonthlyReport> oneMonth = new ArrayList<>();

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {

                    if (monthlyReport == null) {
                        for (int m = 1; m <= 3; m++) {
                            monthlyReport = new MonthlyReport();
                            monthlyReport.oneMonthReport("resources/m.20210" + m + ".csv");
                            oneMonth.add(monthlyReport);
                        System.out.println("Отчет за месяц " + m + " считан");
                    }
                    } else {
                        System.out.println("Произошда ошибка. Такого отчета не существует или он уже считан");
                    }
                }

            if (command == 2) {
                if (yearlyReport == null) {
                    yearlyReport = new YearlyReport(2021, "resources/y.2021.csv");
                    yearlyReport.yearMonth();
                } else {
                        System.out.println("Произошда ошибка. Такого отчета не существует или он уже считан");
                }
            }

            if (command == 3) {
                boolean hasMistake = true;
                    if ((oneMonth.size() == 3) && (yearlyReport != null) && (!yearlyReport.monthsData.isEmpty())) {

                    for (int i = 0; i < 3; i++) {

                        boolean checkReportIncome = (oneMonth.get(i).monthIncome() == yearlyReport.sumIncome(i + 1));
                        boolean checkReportExpense = (oneMonth.get(i).monthExpense() == yearlyReport.sumExpense(i + 1));

                        if (!checkReportIncome || !checkReportExpense) {
                            hasMistake = false;
                            System.out.println("В " + (i + 1) + " месяце допущена ошибка");
                        } else {
                            System.out.println("Данные за " + (i+1) + " ммесяц сверены успешно");
                        }
                    }
                    if (hasMistake) {
                        System.out.println("Сверка отчетов завершена успешно");
                    }
                    } else {
                        System.out.println("Загружены не все отчеты. Повторите попытку снова.");
                }
            }
                    if (command == 4) {

                            if ((oneMonth.size() == 3) && (yearlyReport != null) && (!yearlyReport.monthsData.isEmpty())) {
                                for (int i = 0; i < 3; i++) {
                                System.out.println("Данные за " + (i + 1) + " месяц:");
                                System.out.println("Самая высокая прибыль - " + oneMonth.get(i).maxProfit() + " руб.");
                                System.out.println("Самая большая трата - " + oneMonth.get(i).maxSpending() + " руб.");
                            }
                        } else {
                                System.out.println("Считаны не все отчеты. Необходимо считать месячные отчеты и годовые, после этого повторите попытку.");
                                }
                        }

                    if (command == 5) {
                        if ((null != yearlyReport) && (!yearlyReport.monthsData.isEmpty())) {
                        System.out.println("Информация об отчете за 2021 год");
                        yearlyReport.incomeYear();
                        yearlyReport.expenseYear();
                        yearlyReport.monthProfit();
                    } else {
                            System.out.println("Не считан годовой отчет");
                        }
                    }
            if (command == 0) {
                System.out.println("До новых встречь!");
                break;
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
            System.out.println("0 - завершить программу");
        }
    }






