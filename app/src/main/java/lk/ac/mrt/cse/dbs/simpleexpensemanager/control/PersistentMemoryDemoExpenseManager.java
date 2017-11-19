package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

/**
 * Created by Chuhaa on 18-Nov-17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.ExpenseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.ExpenseManagerException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentMemoryAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentMemoryTransactionDAO;


public class PersistentMemoryDemoExpenseManager extends ExpenseManager {
    Context context;
    public PersistentMemoryDemoExpenseManager(Context ctx)  {
        context=ctx;
        try {

            setup();
        } catch (ExpenseManagerException e) {
            e.printStackTrace();
        }
    }
    @Override

    public void setup() throws ExpenseManagerException {

        SQLiteDatabase db = context.openOrCreateDatabase("150084F", context.MODE_PRIVATE, null);

        // create the databases.
        db.execSQL("CREATE TABLE IF NOT EXISTS Account(" +
                "accountNo VARCHAR PRIMARY KEY," +
                "bankName VARCHAR," +
                "accountHolderName VARCHAR," +
                "balance REAL" +
                " );");


        db.execSQL("CREATE TABLE IF NOT EXISTS Account_Transaction(" +
                "Transaction_id INTEGER PRIMARY KEY," +
                "accountNo VARCHAR," +
                "expenseType INT," +
                "amount REAL," +
                "date DATE," +
                "FOREIGN KEY (accountNo) REFERENCES Account(accountNo)" +
                ");");




        PersistentMemoryAccountDAO accountDAO = new PersistentMemoryAccountDAO(db);

        setAccountsDAO(accountDAO);

        setTransactionsDAO(new PersistentMemoryTransactionDAO(db));
        // dummy data
       /* Account dummyAcct1 = new Account("12345A", "Yoda Bank", "Anakin Skywalker", 10000.0);
        Account dummyAcct2 = new Account("78945Z", "Clone BC", "Obi-Wan Kenobi", 80000.0);
      /*  getAccountsDAO().addAccount(dummyAcct1);
        getAccountsDAO().addAccount(dummyAcct2);*/

        /*** End ***/

    }
}