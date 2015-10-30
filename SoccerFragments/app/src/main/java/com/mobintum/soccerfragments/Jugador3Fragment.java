package com.mobintum.soccerfragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Jugador3Fragment extends Fragment {
    public static final String TAG = "Jugador3Fragment";
    private static final String ARG_PARAM_INSTRUCCION = "paramInstruccion";
    private String instruccion;


    public static Jugador3Fragment newInstance(String instruccion){
        Jugador3Fragment fragment = new Jugador3Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_INSTRUCCION, instruccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            this.instruccion = getArguments().getString(ARG_PARAM_INSTRUCCION);
        }
    }

    public Jugador3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jugador3, container, false);
        TextView txtInstruccion = (TextView) view.findViewById(R.id.txtInstruccion);
        txtInstruccion.setText(instruccion);
        return view;
    }


}
