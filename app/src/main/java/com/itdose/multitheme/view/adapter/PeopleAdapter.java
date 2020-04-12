package com.itdose.multitheme.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.itdose.multitheme.R;
import com.itdose.multitheme.data.remote.model.People;
import com.itdose.multitheme.databinding.ItemPeopleBinding;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private List<People> people;
    private PeopleListener listener;

    public PeopleAdapter() {
        people = new ArrayList<>();
    }

    public void setPeople(List<People> people) {
        this.people.addAll(people);
        notifyDataSetChanged();
    }

    public void setListener(PeopleListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPeopleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_people,
                parent,false);
        return new PeopleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        holder.binding.setItem(people.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class PeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemPeopleBinding binding;
        public PeopleViewHolder(ItemPeopleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null)listener.onItemClick(people.get(getAdapterPosition()));
        }
    }

    public interface PeopleListener{
        void onItemClick(People people);
    }
}
