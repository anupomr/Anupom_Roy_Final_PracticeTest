package com.example.anupo.anupom_roy_final_practicetest;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TaskActivity extends AppCompatActivity {
    private TaskManager taskManager;
    private EditText txtId, txtTaskName, txtTaskAssigned,txtTaskDescription ;
    private Button btnAdd, btnShow, btnEdit, btnDelete;
    private final static String TABLE_NAME = "AndroidTask";

    private static final String tableCreatorString =
            "CREATE TABLE "+ TABLE_NAME + " (taskId integer primary key, taskName text,asaignTo text,taskDescription text);";

    private static final String tableCreatorStringTwo =
            "CREATE TABLE "+ TABLE_NAME + " (taskId integer primary key, taskName text,taskDescription text);";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        //
        txtId = (EditText) findViewById(R.id.txtTaskId);
        txtTaskName = (EditText) findViewById(R.id.txtTaskName);
        txtTaskAssigned=(EditText)findViewById(R.id.txtTaskAssigned);
        txtTaskDescription = (EditText) findViewById(R.id.txtTaskDescription);

        //
        btnAdd = (Button)findViewById(R.id.addTask);
        btnShow = (Button)findViewById(R.id.showTask);
        btnEdit = (Button)findViewById(R.id.editTask);
        //
        // initialize the tables
        try {
            taskManager = new TaskManager(this);
            //create the table
            taskManager.dbInitialize(TABLE_NAME, tableCreatorString);
        }
        catch(Exception exception)
        {
            Toast.makeText(TaskActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }

        //Button hendeler
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask(v);
            }
        });

    }

    public void showTask(View v)
    {
        try {
            Task task = taskManager.getTaskById(txtId.getText().toString(), "taskId");
            txtTaskName.setText(task.getTaskName());
            txtTaskAssigned.setText(task.getAsaignTo());
            txtTaskDescription.setText(task.getTaskDescription());
        }
        catch (Exception exception)
        {
            Toast.makeText(TaskActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }
    }

    //
    public void addTask(View v)
    {
        //read values
        int taskId = Integer.parseInt(txtId.getText().toString());
        String taskName = txtTaskName.getText().toString();
        String asaignTo=txtTaskAssigned.getText().toString();
        String taskDescription = txtTaskDescription.getText().toString();
        //initialize ContentValues object with the new task
        ContentValues contentValues = new ContentValues();
        contentValues.put("taskId",taskId);
        contentValues.put("taskName",taskName);
        contentValues.put("asaignTo",asaignTo);
        contentValues.put("taskDescription",taskDescription);
        //
        try {
            taskManager.addRow(contentValues);
        }
        catch(Exception exception)
        {
            //
            Toast.makeText(TaskActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());

        }
    }
    public void editTask(View v)
    {
        int taskId = Integer.parseInt(txtId.getText().toString());

        String taskName = txtTaskName.getText().toString();
        String asaignTo=txtTaskAssigned.getText().toString();
        String taskDescription = txtTaskDescription.getText().toString();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put("taskId",taskId);
            contentValues.put("taskName",taskName);
            contentValues.put("asaignTo",asaignTo);
            contentValues.put("taskDescription",taskDescription);
            //edit the row
            boolean b=taskManager.editRow(taskId, "taskId", contentValues);
        }
        catch(Exception exception)
        {
            Toast.makeText(TaskActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }
    }
    public void deleteTask(View v)
    {
        int taskId = Integer.parseInt(txtId.getText().toString());


        try{
            //ContentValues contentValues = new ContentValues();
            //contentValues.put("taskId",taskId);

            taskManager.deleteRow(taskId, "taskId");
        }
        catch(Exception exception)
        {
            Toast.makeText(TaskActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }
    }





}
