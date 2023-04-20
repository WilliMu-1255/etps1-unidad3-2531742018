package sv.edu.utec.parcial3_2531742018.clases;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import sv.edu.utec.parcial3_2531742018.Helper.AdminSQLiteOpenHelper;

public class insertarFragment extends Fragment {

   /* AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity().getApplicationContext(), "bodega",null,2);
    SQLiteDatabase bd= admin.getWritableDatabase();
    String cod=txtcodPrd.getText().toString();
    String des=txtDesc.getText().toString();
    String pre=txtPrecio.getText().toString();

    ContentValues informacion =new ContentValues();
                informacion.put("codProducto",cod);
                informacion.put("descripProd",des);
                informacion.put("precioProd",pre);
                try {
        bd.insert("productos", null, informacion);

        Toast.makeText(getActivity().getApplicationContext(), "Se inserto el producto", Toast.LENGTH_LONG).show();
        bd.close();

    } catch (Exception e){
        //pendiente imprimir error
        Toast.makeText(getActivity().getApplicationContext(), ""+e.getCause(), Toast.LENGTH_LONG).show();
    }*/
}
