package com;

import android.graphics.Color;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tasker.chrisou.test3.R;

import static android.support.constraint.Constraints.TAG;

public class FragmentOne extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView:FragmentOne Created.");
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RelativeLayout rl = getActivity().findViewById(R.id.background);
        final Button changeBt = view.findViewById(R.id.changeBT);
        final Button showToast = view.findViewById(R.id.toastBT);

        changeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl.setBackgroundColor(Color.RED);
                changeBt.setTextColor(Color.WHITE);
                showToast.setTextColor(Color.WHITE);
            }
        });
        showToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "You clicked the button.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
