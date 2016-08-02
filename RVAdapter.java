package com.hathy.listsandcards;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import android.support.design.widget.Snackbar;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CardView cv;
        public TextView personName;
        public TextView personAge;
        public ImageView personPhoto;

        public MyPersonViewHolderClick mListener;

        public PersonViewHolder(View itemView, MyPersonViewHolderClick listener) {
            super(itemView);

            mListener = listener;

            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            mListener.clickOnView(view, getPosition());
        }

        public interface  MyPersonViewHolderClick{
            void clickOnView(View view, int position);
        }
    }

    List<Person> persons;

    RVAdapter(List<Person> persons){
        this.persons = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v,new PersonViewHolder.MyPersonViewHolderClick(){
            @Override
        public void clickOnView(View v, int position){
                Person person = persons.get(position);
                Snackbar.make(v, person.getName(), Snackbar.LENGTH_LONG).show();
            }
        });
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).name);
        personViewHolder.personAge.setText(persons.get(i).age);
        personViewHolder.personPhoto.setImageBitmap(persons.get(i).bitmap);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}
