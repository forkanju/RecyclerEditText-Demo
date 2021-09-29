package com.forkan.recycleredittext;

public class ItemDatamodel  {

    int ExpenseId;
    String ExpenseName;
    public ItemDatamodel(int expenseId, String expenseName) {
        ExpenseId = expenseId;
        ExpenseName = expenseName;
    }
    public int getExpenseId() {
        return ExpenseId;
    }
    public String getExpenseName() {
        return ExpenseName;
    }
}