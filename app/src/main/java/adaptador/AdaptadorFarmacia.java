package adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.lautaro.turnos.Farmacia;
import com.example.lautaro.turnos.R;

import java.util.List;

/**
 * Created by Lautaro on 4/10/2017.
 */

public class AdaptadorFarmacia extends ArrayAdapter<Farmacia> {
    LayoutInflater inflater;
    public AdaptadorFarmacia(Context context , List<Farmacia> farmacias){
        super(context, R.layout.farmacia_view , farmacias);
        inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        if(row==null) row=inflater.inflate(R.layout.farmacia_view, parent, false);

        ViewHolder holder=(ViewHolder)row.getTag();
        if(holder==null){
            holder= new ViewHolder(row);
            row.setTag(holder);
        }
        holder.icono.setImageResource(R.mipmap.logo);
        holder.tnombre.setText(this.getItem(position).getNombre());
        holder.tdireccion.setText(this.getItem(position).getCalle().trim() +" " + this.getItem(position).getNumero());
        holder.ttelefono.setText(this.getItem(position).getTelefono());
        return row;
    }


}
