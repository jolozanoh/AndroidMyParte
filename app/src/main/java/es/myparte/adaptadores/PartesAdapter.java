package es.myparte.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import java.util.ArrayList;

import es.myparte.clases.Parte;

/**
 * Created by oscarlozanohernaiz
 * .
 */
public class PartesAdapter extends ArrayAdapter<Parte>
{
    private static class ViewHolder {
        TextView text1;
    }

    public PartesAdapter(Context context, ArrayList<Parte> partess) {
        super(context, android.R.layout.simple_dropdown_item_1line, partess);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Parte parte = getItem(position);

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

        viewHolder.text1.setText(parte.getPar_fecha());

        return convertView;
    }

    @Override
    public long getItemId(int position)
    {

        return getItem(position).getId();
    }
}
