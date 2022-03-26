package org.techtown.list;

import android.view.View;

public interface OnPersonItemClickListener {

    //한 아이템을 클릭하면 이 함수가 호출됨
    public void onItemClick(PersonAdpater.ViewHolder holder, View view, int position);
}
