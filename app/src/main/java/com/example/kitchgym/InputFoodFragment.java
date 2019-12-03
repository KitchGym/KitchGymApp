package com.example.kitchgym;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class InputFoodFragment extends Fragment {

    private OnInputFoodListener mListener;
    Button enter;
    EditText food;
    EditText time;
    EditText calories;
    User user;

    public InputFoodFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_input_food, container, false);

        enter = rootView.findViewById(R.id.enterButton);
        food = rootView.findViewById(R.id.foodEnter);
        time = rootView.findViewById(R.id.timeEnter);
        calories = rootView.findViewById(R.id.calEnter);

        // Input the food into the ListView
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!food.getText().toString().equals("") && !time.getText().toString().equals("") &&
                        !calories.getText().toString().equals("")) {
                    mListener.onInputFood(food.getText().toString().trim(), time.getText().toString().trim(),
                            calories.getText().toString().trim());
                }
                else
                    Toast.makeText(getContext(), "Enter all fields.", Toast.LENGTH_LONG);
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInputFoodListener) {
            mListener = (OnInputFoodListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnInputFoodListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnInputFoodListener {
        void onInputFood(String food, String time, String calories);
    }
}
