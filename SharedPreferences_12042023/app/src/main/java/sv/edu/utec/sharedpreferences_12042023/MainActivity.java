package sv.edu.utec.sharedpreferences_12042023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sv.edu.utec.sharedpreferences_12042023.Helper.AdminSQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    EditText txtcodPrd,txtDesc,txtPrecio;
    Button btnInserts,btnUpdates,btnDeletes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtcodPrd=findViewById(R.id.editTextTextPersonName);
        txtDesc=findViewById(R.id.editTextTextPersonName2);
        txtPrecio=findViewById(R.id.editTextNumberDecimal);
        btnInserts=findViewById(R.id.btnInsert);
        btnUpdates=findViewById(R.id.btnUpdate);
        btnDeletes=findViewById(R.id.btnDelete);

        btnInserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "bodega",null,1);
                SQLiteDatabase bd= admin.getWritableDatabase();
                String cod=txtcodPrd.getText().toString();
                String des=txtDesc.getText().toString();
                String pre=txtPrecio.getText().toString();
                ContentValues informacion=new ContentValues();
                informacion.put("codProducto",cod);
                informacion.put("descripProd",des);
                informacion.put("precioProd",pre);

                bd.insert("productos",null,informacion);
                bd.close();

            }
        });
    }
}