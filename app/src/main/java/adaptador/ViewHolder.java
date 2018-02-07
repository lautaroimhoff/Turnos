package adaptador;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lautaro.turnos.R;

/**
 * Created by Lautaro on 5/10/2017.
 */
public class ViewHolder {
    ImageView icono;
    TextView tnombre,tdireccion,ttelefono;



    ViewHolder(View base){
        tnombre = (TextView) base.findViewById(R.id.tnombre2);
        tdireccion = (TextView) base.findViewById(R.id.tdireccion);
        ttelefono = (TextView) base.findViewById(R.id.ttelefono2);
        icono = (ImageView) base.findViewById(R.id.nuevoicono);
    }

}
