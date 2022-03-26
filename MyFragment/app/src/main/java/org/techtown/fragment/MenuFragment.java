package org.techtown.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;



public class MenuFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView2 = (ViewGroup) inflater.inflate(R.layout.fragment_menu, container, false);
        //인플레이션, xml레이아웃의 내용을 메모리에 올리는 함수

        Button button2 = rootView2.findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//액티비티쪽으로 위임하기
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged(0);
            }
        });
        return rootView2;

    }
}