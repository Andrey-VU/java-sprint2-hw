import java.util.ArrayList;

public class Checker {

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

        for (int i = 0; i < yearlyReport.allData.size() / 2; i++) {
            int sumOfIncomeMR = 0;
            int sumOfExpenseMR = 0;
            for (Record recordCh : monthlyReport.allData.get(yearlyReport.namesMonth[i])) {
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
        boolean tmpBoolean = false;
        for (int j = 0; j < yearlyReport.allData.size() / 2; j++) {
            if ((incomeYR.get(j).equals(incomeMR.get(j))) & (expenseMR.get(j).equals(expenseMR.get(j)))) {
                tmpBoolean = true;
            }
            else {
                wrongMonth = yearlyReport.namesMonth[j];
                tmpBoolean = false;
            }
        }
        return tmpBoolean;
    }



}
