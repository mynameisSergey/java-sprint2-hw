import java.util.HashMap;
import java.util.Objects;


public class MonthlyReport {
    int month;
    public MonthlyReport(int month) {
        this.month = month;
    }
    public HashMap<String, MonthData> months = new HashMap<>();

    public void oneMonthReport(String path) {
        String content = YearlyReport.readFileContentsOrNull(path);
        if (content == null || content.isEmpty()) {
            System.out.println("Файл пуст или не найден.");
        } else {
            String[] lines = Objects.requireNonNull(content).split("\n"); // массив строк
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
    }

     Integer monthIncome() { // расчет дохода по месяцу
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

    public void maxProfit() { // максимальная прибыль за товар
        int max = 0;
        String name = "";
        for (MonthData monthData  : months.values()) {
                if (max < monthData.income) {
                max = monthData.income;
                name = monthData.itemName;
            }
        }
        if (max > 0) { // Проверяем, были ли расходы
            System.out.println("Больше всего прибыли за " + name + " на общую сумму " + max);
        } else {
            System.out.println("Нет данных о расходах.");
        }

    }

    public void maxExpense() { // максимальная трата на товар
        int max = 0;
        String name = "";

        for (MonthData monthData : months.values()) {
            if (max < monthData.expense) {
                max = monthData.expense;
                name = monthData.itemName;
            }
        }

        if (max > 0) { // Проверяем, были ли расходы
            System.out.println("Больше всего потратили на " + name + " на общую сумму " + max);
        } else {
            System.out.println("Нет данных о расходах.");
        }
    }
}
