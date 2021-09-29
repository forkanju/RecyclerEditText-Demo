package com.forkan.recycleredittext;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    View rootView;
    Context context;
    private ArrayList<ItemDatamodel> dataSet;
    // Usered Entered Expense amt will be stored in ExpAmtArray
    ArrayList<String> ExpNameArray = new ArrayList<String>();
    ArrayList<String> ExpAmtArray = new ArrayList<String>();
    boolean isOnTextChanged = false;
    int ExpenseFinalTotal = 0;
    TextView textviewTotalExpense;

    //constructor with dataSet passed from MainActivity when Adapter is called
    public Adapter(ArrayList<ItemDatamodel> dataSet) {
        this.dataSet = dataSet;
    }

    //Recyclerview ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView expensesName;
        EditText expHeld;
        Button imageButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // finding view by view id.
            expensesName = (TextView) itemView.findViewById(R.id.textViewExpName);
            expHeld = (EditText) itemView.findViewById(R.id.ExpHeld);
            imageButton = (Button) itemView.findViewById(R.id.ExpBimageSelect);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Initialize view for recyclerview
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_pord_list, parent, false);
        context = parent.getContext();
        rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        textviewTotalExpense = (TextView) rootView.findViewById(R.id.totalExpense);
        //attach view to MyViewHolder
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //initialize the view with view holder
        TextView expensesName = holder.expensesName;
        EditText expHeld = holder.expHeld;
        Button imageButton = holder.imageButton;
        int id = dataSet.get(position).getExpenseId();

        expensesName.setText(dataSet.get(position).getExpenseName());
        // EditText with TextWatcher Listens each time when user enter value in edittext in recyclerview
        expHeld.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //using this boolean because sometime when user enter value in edittxt
                //afterTextchanged runs twice to prevent this, i m making use of this variable.
                isOnTextChanged = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //so this will trigger each time user enter value in editText box
                ExpenseFinalTotal = 0;
                if (isOnTextChanged) {
                    isOnTextChanged = false;
                    try {
                        ExpenseFinalTotal = 0;
                        for (int i = 0; i <= id; i++) {
                            if (i != id) {
                                //store 0  where user select position in not equal/
                                ExpNameArray.add("0");
                                ExpAmtArray.add("0");
                            } else {
                                // store user entered value to Array list (ExpAmtArray) at particular position
                                ExpAmtArray.add("0");
                                ExpNameArray.add("0");

                                ExpNameArray.set(id, dataSet.get(id).getExpenseName());
                                ExpAmtArray.set(id, editable.toString());
                            }
                        }
                        Log.d("ExpAmt", ExpAmtArray.toString());
                        Log.d("ExpName", ExpNameArray.toString());

                    } catch (NumberFormatException e) {

                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}