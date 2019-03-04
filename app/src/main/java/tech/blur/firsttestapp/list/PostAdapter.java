package tech.blur.firsttestapp.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import tech.blur.firsttestapp.R;
import tech.blur.firsttestapp.core.model.Post;

//TODO Доделать адаптер
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private final List<Post> posts = new ArrayList<>();
    private final LayoutInflater inflater;
    private PrioritySelectListener prioritySelectListener;

    public PostAdapter(Context context, PrioritySelectListener prioritySelectListener) {
        this.prioritySelectListener = prioritySelectListener;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.post_item, parent, false);
        return new PostHolder(itemView, prioritySelectListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.bind(posts.get(position), position);
    }

    public void setPosts(List<Post> postsList) {
        posts.clear();
        if (postsList != null) posts.addAll(postsList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    class PostHolder extends RecyclerView.ViewHolder {


        private final TextView body;
        private final TextView title;
        private final ChipGroup chipGroup;
        private final PrioritySelectListener prioritySelectListener;


        public PostHolder(@NonNull View itemView, PrioritySelectListener prioritySelectListener) {
            super(itemView);
            body = itemView.findViewById(R.id.item_body);
            title = itemView.findViewById(R.id.item_title);
            chipGroup = itemView.findViewById(R.id.item_chip_group);
            this.prioritySelectListener = prioritySelectListener;
        }

        public void bind(final Post post, int pos) {
            String title_str = post.getId() + ". " + post.getTitle();
            body.setText(post.getBody());
            title.setText(title_str);
            switch (post.getPriority()) {
                case 0: {
                    chipGroup.check(R.id.item_priority_normal);
                    break;
                }
                case 1: {
                    chipGroup.check(R.id.item_priority_middle);
                    break;
                }
                case 2: {
                    chipGroup.check(R.id.item_priority_high);
                    break;
                }
            }
            chipGroup.setOnCheckedChangeListener((chipGroup, i) -> {
                prioritySelectListener.OnPrioritySelectedListener(chipGroup, i, post, pos);
                chipGroup.getCheckedChipId();
            });

        }
    }


    public interface PrioritySelectListener {
        void OnPrioritySelectedListener(ChipGroup chipGroup, int i, Post post, int pos);
    }
}


