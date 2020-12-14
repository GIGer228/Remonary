package com.example.remonary.RecyclerViewTools;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remonary.R;
import com.example.remonary.DataSet.WordElement;

import java.util.Collections;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordViewHolder> {

    private List<WordElement> items = Collections.emptyList();
    private final WordAdapterCallbacks callbacks;

    public WordAdapter(WordAdapterCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dictionary_item_view, parent, false);

        return new WordViewHolder(view, new ViewHolderAction() {
            @Override
            public void action(int position) {
                callbacks.onWordClick(items.get(position));
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    public void setItems(List<WordElement> items) {
        WordDiffCallback
        this.items = items;
        notifyDataSetChanged();
    }
}
