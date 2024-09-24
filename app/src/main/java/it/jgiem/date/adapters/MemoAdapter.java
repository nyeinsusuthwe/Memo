package it.jgiem.date.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.jgiem.date.databinding.AdapterListBinding;
import it.jgiem.date.entities.Memo;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoViewHolder> {

    private List<Memo> memos;
    private MemoClickListener listener;

    public MemoAdapter(List<Memo> memos, MemoClickListener listener){
        this.memos = memos;
        this.listener = listener;
    }

    public interface MemoClickListener{
        void onMemoClicked(Memo memo);
    }

    @NonNull
    @Override
    public MemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        var binding = AdapterListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MemoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoViewHolder holder, int position) {
        var memo = memos.get(position);
        holder.binding.tvName.setText(memo.getName());
        holder.binding.tvDate.setText(memo.getDate());
        holder.binding.tvContact.setText(memo.getContact());
        holder.binding.cv.setOnClickListener(view -> listener.onMemoClicked(memo));
    }

    @Override
    public int getItemCount() {
        return memos.size();
    }

    public void setMemos(List<Memo> memos){
        this.memos = memos;
        this.notifyDataSetChanged();
    }

    class MemoViewHolder extends RecyclerView.ViewHolder{

        public AdapterListBinding binding;

        public MemoViewHolder(AdapterListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
