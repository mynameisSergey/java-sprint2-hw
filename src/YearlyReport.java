import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class YearlyReport {
    public int year;
    public HashMap<Integer, YearlyReportMonth> monthsData = new HashMap<>();


    public YearlyReport(int year) {
        this.year = year;
    }

    void yearReport(String path) {
         String content = readFileContentsOrNull(path); // << содержимое файла
        if (content == null || content.isEmpty()) {
            System.out.println("Файл пуст или не найден.");
        } else {
        String[] lines = content.split("\n");

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            if (!monthsData.containsKey(month)) {
                monthsData.put(month, new YearlyReportMonth(month));
            }

            YearlyReportMonth oneMonthData = monthsData.get(month);
            if (isExpense) {
                oneMonthData.expenses += amount;
            } else {
                oneMonthData.income += amount;
            }
        }
        System.out.println("Считывание годового отчета завершено успешно");
    }
}



    Integer sumProfitMonthOfYear(int month) { // подсчет дохода за месяц в году
        int income = 0;
        YearlyReportMonth oneMonth = monthsData.get(month);
        if(oneMonth != null) {
            income = oneMonth.income;
        }
        return income;
    }
    Integer sumExpensesMonthOfYear(int month)  { // подсчет расходов за месяц в году
        int expense = 0;
        YearlyReportMonth oneMonth = monthsData.get(month);
        if(oneMonth != null) {
            expense = oneMonth.expenses;
        }
        return expense;
    }


        public void monthProfit() { // расчет прибыли по всем месяцам в году
        for(YearlyReportMonth oneMonthData : monthsData.values()) {
            System.out.println("Месяц " + oneMonthData.month + " прибыль: " + (oneMonthData.income - oneMonthData.expenses));
        }
        }

        public void differentProfit() { //расчет среднего дохода за всемесяцы в году
            int sumIncome = 0;
            int i = 0;
                for (YearlyReportMonth oneMonthData : monthsData.values()) {
                    sumIncome += oneMonthData.income;
                    i++;
                }
            System.out.println("Средний доход за все месяцы в году: " + (sumIncome/i));
        }

    public void differentExpenses() { //расчет среднего расхода за всемесяцы в году
        int sumExpense = 0;
        int i = 0;
        for (YearlyReportMonth oneMonthData : monthsData.values()) {
            sumExpense += oneMonthData.expenses;
            i++;
        }
        System.out.println("Средний расход за все месяцы в году: " + (sumExpense/i));
    }

    public static String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
        }
    }
