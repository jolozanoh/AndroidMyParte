package es.myparte.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import java.util.ArrayList;

import es.myparte.clases.Aseguradora;

/**
 * Created by oscarlozanohernaiz.
 */
public class EmergenciasAdapter extends ArrayAdapter<Aseguradora>
{
    private static class ViewHolder {
        TextView text1;
    }

    public EmergenciasAdapter(Context context, ArrayList<Aseguradora> aseguradorass) {
        super(context, android.R.layout.simple_dropdown_item_1line, aseguradorass);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Aseguradora aseguradora = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
            viewHolder.text1 = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text1.setText(aseguradora.getTelefono());

        return convertView;
    }

    @Override
    public long getItemId(int position)
    {
        return getItem(position).getId();
    }
}
