package com;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tasker.chrisou.test3.R;

import java.security.PrivateKey;

import static android.support.constraint.Constraints.TAG;

public class FragmentThree extends DialogFragment {

    private static String TAG = "FragmentThree";
    private EditText editText;
    private Button button;

    public interface OnInputListener{
        void sendInput(String input);
    }
    OnInputListener onInputListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        editText = view.findViewById(R.id.EditText);
        button = view.findViewById(R.id.Button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: click floating button.");
                String input = editText.getText().toString();
                if(!input.equals("")){
                    onInputListener.sendInput(input);
                    getDialog().dismiss();
                }
            }
        });
        return  view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onInputListener = (OnInputListener)getTargetFragment();//getTargetFragment()
        }catch (ClassCastException e){
            Log.d(TAG, "onAttach: Exception" + e.getMessage());
        }
    }
}



























