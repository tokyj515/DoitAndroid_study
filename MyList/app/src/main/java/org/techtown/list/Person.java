package org.techtown.list;

//리싸이클러뷰, 선택위젯
//어뎁터 사용해서 선택
//한 아이템을 위한 데이터 정리
public class Person {

    String name;
    String mobile;

    public Person(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
