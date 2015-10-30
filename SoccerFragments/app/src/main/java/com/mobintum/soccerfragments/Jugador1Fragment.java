package com.mobintum.soccerfragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


public class Jugador1Fragment extends Fragment {

    public static final String TAG = "Jugador1Fragment";
    private static final String ARG_PARAM_INSTRUCCION = "paramInstruccion";
    private String instruccion;
    private TextView txtInstruccion;


    private OnFragmentInteractionListener mListener;


    public static Jugador1Fragment newInstance(String instruccion) {
        Jugador1Fragment fragment = new Jugador1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_INSTRUCCION, instruccion);
        fragment.setArguments(args);
        return fragment;
    }

    public Jugador1Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.instruccion = getArguments().getString(ARG_PARAM_INSTRUCCION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jugador1, container, false);
        txtInstruccion = (TextView) view.findViewById(R.id.txtInstruccion);
        txtInstruccion.setText(instruccion);
        txtInstruccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.mensaje("GOOOL");
            }
        });

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        public void mensaje(String msg);
    }

    public void setInstruccion(String instruccion){
        txtInstruccion.setText(instruccion);

    }

}
