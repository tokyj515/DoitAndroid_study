package org.techtown.fragment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ViewerFragment extends Fragment {

    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewer, container, false);
        imageView = rootView.findViewById(R.id.imageView);

        return rootView;
    }


    //프래그먼티끼리 바로 바꿀 수 없기 때문에 액티비티를 통해 바꾸는 방법법
   public void setImage(int resId){ //이미지뷰에서 보이는 이미지를 바꾸기 위한 함수
        imageView.setImageResource(resId);
    }
}
