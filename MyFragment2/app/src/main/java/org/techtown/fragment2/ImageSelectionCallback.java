package org.techtown.fragment2;

public interface ImageSelectionCallback {
    //프래그먼트는 재사용을 하기 위해 만드는 것으로 코드가 바뀌지 않는 것이 좋음
    //그래서 인터페이스를 선언해서 인터페이스를 통해 구현
    public void onImageSecleted(int position);

}
