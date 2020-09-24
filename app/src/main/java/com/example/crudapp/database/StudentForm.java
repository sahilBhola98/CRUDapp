package com.example.crudapp.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.crudapp.StudentInputForm;
import com.example.crudapp.database.SAO;
import com.example.crudapp.R;
import com.example.crudapp.database.StudentContract.FeedEntry;

public class StudentForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);
    }

    public void clickListener(View view) {
        Context context = view.getRootView().getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementViews = inflater.inflate(R.layout.activity_student_input_form , null , false);

        final EditText studentName = (EditText) formElementViews.findViewById(R.id.editTextStudentFirstName);
        final EditText studentEmail = (EditText) formElementViews.findViewById(R.id.editTextStudentEmail);
        final Context contexts = this;
        final SAO sao = new SAO(contexts);
        ListView dblistView = findViewById(R.id.dblist);
        Cursor dataCursor  =  sao.readRows();
        CursorAdapter adapter = new SimpleCursorAdapter(StudentForm.this,android.R.layout.simple_list_item_2,dataCursor,
        new String[]{FeedEntry.COLUMN_NAME,FeedEntry.COLUMN_EMAIL},
                new int[]{android.R.id.text1,android.R.id.text2});
        dblistView.setAdapter(adapter);
        dblistView.setTag(FeedEntry._ID);

        String id;


        dblistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final Context context2=view.getContext();

                final CharSequence[] items = { "Edit", "Delete" };
                new AlertDialog.Builder(context2).setTitle("Student Record")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                if(item==0){
                                    //editRecord(Integer.parseInt(FeedEntry._ID));
                                    LayoutInflater inflater = (LayoutInflater) context2.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    final View formView = inflater.inflate(R.layout.activity_student_input_form,null,false);
                                    final EditText editTextStudent = (EditText) formView.findViewById(R.id.editTextStudentFirstName);
                                    final EditText editTextStudentEmail = (EditText) formView.findViewById(R.id.editTextStudentEmail);
                                    editTextStudent.setText(FeedEntry.COLUMN_NAME);
                                    editTextStudentEmail.setText(FeedEntry.COLUMN_EMAIL);
                                    new AlertDialog.Builder(context2)
                                            .setView(formView)
                                            .setTitle("Edit Record")
                                            .setPositiveButton("Save Change",
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            String updateName = editTextStudent.getText().toString();
                                                            String updateEmail = editTextStudentEmail.getText().toString();

                                                            boolean updated = sao.update(updateName,updateEmail);
                                                            if(updated){
                                                                Toast.makeText(StudentForm.this, "Updated success", Toast.LENGTH_SHORT).show();
                                                            }else{
                                                                Toast.makeText(StudentForm.this, "Update failed", Toast.LENGTH_SHORT).show();
                                                            }
                                                            dialog.cancel();
                                                        }
                                                    }).show();
                                }else if(item==1){

                                    boolean deleteSuccess = sao.delete(FeedEntry.COLUMN_NAME);
                                    if(deleteSuccess){
                                        Toast.makeText(context2, "Deleted Successfully", Toast.LENGTH_SHORT).show();

                                    }
                                    else{
                                        Toast.makeText(context2, "Not deleted", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                dialog.dismiss();
                            }

                            private void editRecord(final int studentID) {

                            }
                        }).show();
                return false;
            }



        });


        new AlertDialog.Builder(context).setView(formElementViews).setTitle("Create Student").setPositiveButton("Add",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog , int id){
                String firstName = studentName.getText().toString();
                String email = studentEmail.getText().toString();
                sao.openDb();
                
                boolean success = sao.createRow(firstName,email);
                String result = sao.readRow();

                TextView resultView = findViewById(R.id.textViewDb);
                resultView.setText(result);

                sao.closeDb();
                /**
                 * Create request here
                 */
                if(success){
                    Toast.makeText(contexts, "Student information saved", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(contexts, "Unable to save", Toast.LENGTH_SHORT).show();
                }
                dialog.cancel();
            }
        }).show();

    }
}