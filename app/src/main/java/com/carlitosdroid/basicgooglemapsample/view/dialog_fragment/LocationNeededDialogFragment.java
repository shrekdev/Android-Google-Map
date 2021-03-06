package com.carlitosdroid.basicgooglemapsample.view.dialog_fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.carlitosdroid.basicgooglemapsample.R;
import com.carlitosdroid.basicgooglemapsample.listener.OnClickLocationListener;

public class LocationNeededDialogFragment extends DialogFragment implements View.OnClickListener{

    AppCompatButton btnLocationNeeded;
    AppCompatTextView lblMessageBody;
    OnClickLocationListener onClickLocationListener;
    private String message;

    public static LocationNeededDialogFragment newInstance(String message){
        LocationNeededDialogFragment locationNeededDialogFragment = new LocationNeededDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        locationNeededDialogFragment.setArguments(bundle);
        return locationNeededDialogFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity){
            activity = (Activity) context;
            onClickLocationListener = (OnClickLocationListener) activity;
        }
    }

    @Override
    public void onDetach() {
        this.onClickLocationListener = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = getArguments().getString("message");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_location_needed, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        btnLocationNeeded = (AppCompatButton) view.findViewById(R.id.btnLocationNeeded);
        lblMessageBody = (AppCompatTextView) view.findViewById(R.id.lblMessageBody);
        lblMessageBody.setText(message);
        btnLocationNeeded.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        getDialog().dismiss();
        onClickLocationListener.onShowLocationPermission();
    }
}