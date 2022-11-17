import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;


public class MonthlyReport {

    public HashMap<String, MonthData> months = new HashMap<>();

    public void oneMonthReport(String path) {

        String content = readFileContentsOrNull(path);
        assert content != null;
        String[] lines = content.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);
            if (!months.containsKey(itemName)) {
                months.put(itemName, new MonthData(itemName, quantity, sumOfOne, isExpense));
            }
            MonthData oneData = months.get(itemName);
            if (isExpense) {
                oneData.expense = quantity * sumOfOne;
            } else {
                oneData.income = quantity * sumOfOne;
            }
        }
    }

    private String readFileContentsOrNull(String monthPath) {
        try {
            return Files.readString(Path.of(monthPath));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }


    public Integer monthIncome() { // расчет дохода по месяцу
        int income = 0;
        for (MonthData oneMonth : months.values()) {
            income += oneMonth.income;
        }
        return income;
    }

    public Integer monthExpense() { // расчет расходов по месяцу
        int expense = 0;
        for (MonthData oneMonth : months.values()) {
            expense += oneMonth.expense;
        }
        return expense;
    }

    public String maxProfit() { // максимальная прибыль за товар
        int max = 0;
        String name = "";
        for (String itemName : months.keySet()) {
            MonthData monthData = months.get(itemName);
            if (max < monthData.income) {
                max = monthData.income;
                name = monthData.itemName;
            }
        }
        return name + " " + max;
    }

    public String maxSpending() { // максимальная трата на товар
        int max = 0;
        String name = "";
        for (String itemName : months.keySet()) {
            MonthData monthData = months.get(itemName);
            if (max < monthData.expense) {
                max = monthData.expense;
                name = monthData.itemName;
            }
        }
        return name + " " + max;
        }
}
