package sv.edu.utec.parcial3_2531742018;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import sv.edu.utec.parcial3_2531742018.Helper.AdminSQLiteOpenHelper;
import sv.edu.utec.parcial3_2531742018.clases.insertarFragment;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre,txtApellido , txtTelefono, txtCorreo;
    BottomNavigationView btnNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNav=findViewById(R.id.btnNav);
        txtNombre=findViewById(R.id.edtNombre);
        txtApellido=findViewById(R.id.edtApellido);
        txtTelefono=findViewById(R.id.edtTelefono);
        txtCorreo=findViewById(R.id.edtCorreo);


        btnNav.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) navaListener);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_configuraciones,menu);
        return true ;

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navaListener= new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment seleccionFrag= null;

            switch (item.getItemId()){
                case R.id.nav_home:
                    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(),"Parcial",null,2);
                    SQLiteDatabase bd= admin.getWritableDatabase();
                    String tel=txtTelefono.getText().toString();
                    String nom=txtNombre.getText().toString();
                    String ape=txtApellido.getText().toString();
                    String cor=txtCorreo.getText().toString();

                    ContentValues informacion =new ContentValues();
                    informacion.put("idTel",tel);
                    informacion.put("nombre",nom);
                    informacion.put("apellido",ape);
                    informacion.put("correo",cor);
                    try {
                        bd.insert("Contactos", null, informacion);

                        Toast.makeText(getApplicationContext(), "Se inserto el contacto", Toast.LENGTH_LONG).show();
                        bd.close();

                    } catch (Exception e){
                        //pendiente imprimir error
                        Toast.makeText(getApplicationContext(), ""+e.getCause(), Toast.LENGTH_LONG).show();
                    }
                    bd.close();
                    break;
                case R.id.nav_fav:

                    AdminSQLiteOpenHelper admin2 = new AdminSQLiteOpenHelper(getApplicationContext(),"Parcial",null,2);
                    SQLiteDatabase bd2= admin2.getWritableDatabase();
                    String tel2=txtTelefono.getText().toString();
                    String nom2=txtNombre.getText().toString();
                    String ape2=txtApellido.getText().toString();
                    String cor2=txtCorreo.getText().toString();
                    ContentValues informacion2 =new ContentValues();
                    informacion2.put("idTel",tel2);
                    informacion2.put("nombre",nom2);
                    informacion2.put("apellido",ape2);
                    informacion2.put("correo",cor2);

                    int cat=bd2.update("Contactos",informacion2,
                            "idTel="+tel2,null);
                    bd2.close();
                    if(cat==1){
                        Toast.makeText(getApplicationContext(),"Se modifico el producto",Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"No se modifico el producto",Toast.LENGTH_LONG).show();

                    }

                    break;
                case R.id.nav_cam:
                    AdminSQLiteOpenHelper admin3 = new AdminSQLiteOpenHelper(getApplicationContext(),"Parcial",null,2);
                    SQLiteDatabase bd3= admin3.getWritableDatabase();
                    String tel3=txtTelefono.getText().toString();

                    int cat2=bd3.delete("Contactos",
                            "idTel="+tel3,null);
                    bd3.close();

                    txtTelefono.setText("");
                    txtNombre.setText("");
                    txtApellido.setText("");
                    txtCorreo.setText("");

                    if(cat2==1){
                        Toast.makeText(getApplicationContext(),"Se borro el producto",Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"No se borro el producto",Toast.LENGTH_LONG).show();

                    }

                    break;
            }

            return true;

        }



    };

    public boolean onOptionsItemSelected(MenuItem items){


        switch (items.getItemId()){

            case R.id.opcion1:

                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "Parcial",null,2);
                SQLiteDatabase bd= admin.getWritableDatabase();

                String nom=txtNombre.getText().toString();

                Cursor filas=bd.rawQuery("SELECT apellido, idTel, correo FROM Contactos WHERE nombre = '"+nom+"'", null);

                if(filas.moveToFirst()){
                    txtApellido.setText(filas.getString(0));
                    txtTelefono.setText(filas.getString(1));
                    txtCorreo.setText(filas.getString(2));

                }
                else{
                    Toast.makeText(getApplicationContext(),"No se encontro" +
                            " el producto",Toast.LENGTH_LONG).show();

                }
                bd.close();

                return true;
            case R.id.opcion2:

                AdminSQLiteOpenHelper admin2 = new AdminSQLiteOpenHelper(getApplicationContext(), "Parcial",null,2);
                SQLiteDatabase bd2= admin2.getWritableDatabase();

                String ap=txtApellido.getText().toString();

                Cursor filas2=bd2.rawQuery("SELECT nombre, idTel, correo FROM Contactos WHERE apellido = '"+ap+"'", null);

                if(filas2.moveToFirst()){
                    txtNombre.setText(filas2.getString(0));
                    txtTelefono.setText(filas2.getString(1));
                    txtCorreo.setText(filas2.getString(2));

                }
                else{
                    Toast.makeText(getApplicationContext(),"No se encontro" +
                            " el producto",Toast.LENGTH_LONG).show();

                }
                bd2.close();
                return true;
            case R.id.opcion3:

                AdminSQLiteOpenHelper admin3 = new AdminSQLiteOpenHelper(getApplicationContext(), "Parcial",null,2);
                SQLiteDatabase bd3= admin3.getWritableDatabase();

                String tel=txtTelefono.getText().toString();

                Cursor filas3=bd3.rawQuery("SELECT nombre, apellido, correo FROM Contactos WHERE idTel = '"+tel+"'", null);

                if(filas3.moveToFirst()){
                    txtNombre.setText(filas3.getString(0));
                    txtApellido.setText(filas3.getString(1));
                    txtCorreo.setText(filas3.getString(2));

                }
                else{
                    Toast.makeText(getApplicationContext(),"No se encontro" +
                            " el producto",Toast.LENGTH_LONG).show();

                }
                bd3.close();

                return true;
            case R.id.opcion4:

                AdminSQLiteOpenHelper admin4 = new AdminSQLiteOpenHelper(getApplicationContext(), "Parcial",null,2);
                SQLiteDatabase bd4= admin4.getWritableDatabase();

                String cor=txtCorreo.getText().toString();

                Cursor filas4=bd4.rawQuery("SELECT nombre, apellido, idTel FROM Contactos WHERE correo = '"+cor+"'", null);

                if(filas4.moveToFirst()){
                    txtNombre.setText(filas4.getString(0));
                    txtApellido.setText(filas4.getString(1));
                    txtTelefono.setText(filas4.getString(2));

                }
                else{
                    Toast.makeText(getApplicationContext(),"No se encontro" +
                            " el producto",Toast.LENGTH_LONG).show();

                }
                bd4.close();

                return true;


        }

        return super.onOptionsItemSelected(items);
    }
}