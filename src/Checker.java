import java.util.ArrayList;

public class Checker {
    String[] namesMonth = {"Январь", "Февраль", "Март"};
    MonthlyReport monthlyReport;
    YearlyReport yearlyReport;
    ArrayList<Integer> incomeYR = new ArrayList<>(); // Из годовых отчётов все суммарные помесячные доходы
    ArrayList<Integer> expenseYR = new ArrayList<>(); // Из годовых отчётов все суммарные помесячные расходы
    ArrayList<Integer> incomeMR = new ArrayList<>(); // Из месячных отчётов все суммарные помесячные доходы
    ArrayList<Integer> expenseMR = new ArrayList<>(); // Из месячных отчётов все суммарные помесячные расходы
    int allIncomesYR;
    int allExpensesYR;
    String wrongMonth;

    public Checker(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;

        int sumOfIncome = 0;
        int sumOfExpense = 0;
        for (int i = 0; i < yearlyReport.allData.size(); i++) {
            int expense;
            int income;
            if (yearlyReport.allData.get(i).isExpense) {
                expense = yearlyReport.allData.get(i).sumOfOne;
                sumOfExpense += expense;
                expenseYR.add(expense);
            }
            else {
                income = yearlyReport.allData.get(i).sumOfOne;
                sumOfIncome += income;
                incomeYR.add(income);
                }
        }
        allIncomesYR = sumOfIncome;
        allExpensesYR = sumOfExpense;

        for (int i = 0; i < namesMonth.length; i++) {
            int sumOfIncomeMR = 0;
            int sumOfExpenseMR = 0;
            for (Record recordCh : monthlyReport.allData.get(namesMonth[i])) {
                if (recordCh.isExpense) {
                    sumOfExpenseMR += recordCh.quantity * recordCh.sumOfOne;
                }
                else {
                    sumOfIncomeMR += recordCh.quantity * recordCh.sumOfOne;
                }
            }
            expenseMR.add(sumOfExpenseMR);
            incomeMR.add(sumOfIncomeMR);
        }
    }

    public boolean check() {
        String tmpBoolean = "false";
        for (int j = 0; j < namesMonth.length; j++) {
            if ((incomeYR.get(j) == incomeMR.get(j)) & (expenseMR.get(j) == expenseMR.get(j))) {
                tmpBoolean = "true";
            }
            else {
                wrongMonth = namesMonth[j];
                tmpBoolean = "false";
            }
        }
        return Boolean.parseBoolean(tmpBoolean);
    }
    public void printYReport() {
        for (int i = 0; i < namesMonth.length; i++) {
            System.out.println("        " + namesMonth[i] + "  " + (incomeYR.get(i) - expenseYR.get(i)) + "рублей.");
        }
        System.out.println("   средний расход за все месяцы составил: " + allExpensesYR / expenseYR.size() );
        System.out.println("   средний доход за все месяцы составил: " + allIncomesYR / incomeYR.size() );
        }

}
