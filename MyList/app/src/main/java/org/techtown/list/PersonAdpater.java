package org.techtown.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdpater extends RecyclerView.Adapter<PersonAdpater.ViewHolder> {

    ArrayList<Person> items = new ArrayList<Person>();
    OnPersonItemClickListener listener;

    public void addItem(Person item){
        items.add(item);
    }


    public void setItems(ArrayList<Person> items){
        this.items = items;
    }

    public Person getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, Person item){
        items.set(position, item);
    }


    public void setOnItemClickListener(OnPersonItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //viewHolder생성 시점에 실행됨
        //인플래이션
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.person_item, parent,false);

        //return new ViewHolder(itemView);
        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //뷰홀더는 재사용됨
        Person item = items.get(position);
        holder.setItem(item);
        //뷰홀더를 새로 만들지는 않고 데이터만 설정해줌
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView2;

        public ViewHolder(View itemView, final OnPersonItemClickListener listener){
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Person item){
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
        }

    }



}
