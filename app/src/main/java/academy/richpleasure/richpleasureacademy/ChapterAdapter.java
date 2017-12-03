package academy.richpleasure.richpleasureacademy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    private Context mCtx;
    private List<Chapters> chaptersList;

    public ChapterAdapter(Context mCtx, List<Chapters> chaptersList) {
        this.mCtx = mCtx;
        this.chaptersList = chaptersList;
    }


    @Override
    public ChapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.chapter_item,null);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChapterViewHolder holder, int position) {
        Chapters chapters = chaptersList.get(position);
        holder.chapterTitle.setText(chapters.getChapter_title());
        holder.chapterCurrentProgress.setText(chapters.getChapter_current_progress());
    }

    @Override
    public int getItemCount() {
        return chaptersList.size();
    }

    class ChapterViewHolder extends RecyclerView.ViewHolder{

        ImageView chapterIcon;
        TextView chapterTitle,chapterCurrentProgress;
        public ChapterViewHolder(View itemView) {
            super(itemView);

//            chapterIcon = (ImageView) itemView.findViewById(R.id.chapterIcon);
//            chapterTitle = (TextView) itemView.findViewById(R.id.chapterTitle);
//            chapterCurrentProgress = (TextView) itemView.findViewById(R.id.chapterCurrentProgress);
        }
    }
}
