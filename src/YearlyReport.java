import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Objects;

public class YearlyReport {
    public int year;
    public String path;
    public HashMap<Integer, YearlyReportMonth> monthsData = new HashMap<>();


    public YearlyReport(int year, String path) {
        this.year = year;
        this.path = path;
    }

public void yearMonth() {
    String content = readFileContentsOrNull(path); // << содержимое файла
    if (content != null) {

        String[] lines = Objects.requireNonNull(content).split("\n"); // массив строк

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i]; //"01, 350000, true"
            String[] parts = line.split(","); //["01", "35000", "true"]
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
                oneMonthData.amount += amount;
            }
        }
        System.out.println("Считывание годового отчета завершено успешно");
    }
}



    public int sumIncome(int month) { // подсчет дохода за месяц в году
        int income = 0;
        YearlyReportMonth oneMonth = monthsData.get(month);
        if(oneMonth != null) {
            income = oneMonth.amount;
        }
        return income;
    }
    public int sumExpense(int month) { // подсчет расходов за месяц в году
        int expense = 0;
        YearlyReportMonth oneMonth = monthsData.get(month);
        if(oneMonth != null) {
            expense = oneMonth.expenses;
        }

        return expense;
    }


        public void monthProfit() { // расчет прибыли по всем месяцам в году
        for(YearlyReportMonth oneMonthData : monthsData.values()) {
            System.out.println("месяц " + oneMonthData.month + " прибыль: " + (oneMonthData.amount - oneMonthData.expenses));
        }
        }

        public void incomeYear() { //расчет среднего дохода за всемесяцы в году
        int sumIncome = 0;
        int i = 0;
        for (YearlyReportMonth oneMonthData : monthsData.values()) {
            sumIncome += oneMonthData.amount;
            i++;
    }
        System.out.println("Средний доход за все месяцы в году: " + (sumIncome/i));
    }

    public void expenseYear() { //расчет среднего расхода за всемесяцы в году
        int sumExpense = 0;
        int i = 0;
        for (YearlyReportMonth oneMonthData : monthsData.values()) {
            sumExpense += oneMonthData.expenses;
            i++;
        }
        System.out.println("Средний расход за все месяцы в году: " + (sumExpense/i));
    }

    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
        }
    }
