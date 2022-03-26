package org.techtown.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainFragment extends Fragment {


    @Override //가장 필요한 부분
    //xml에 있는 모습을 인플레이션해서 프레그먼트에 연결
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //자바파일과 xml연결하는 함수

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        //인플레이션, xml레이아웃의 내용을 메모리에 올리는 함수

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//액티비티쪽으로 위임하기
                MainActivity activity = (MainActivity) getActivity(); //내 프래그먼트가 올라가 있는 액티비티 참조하기
                activity.onFragmentChanged(1);
            }
        });
        return rootView;
    }




}