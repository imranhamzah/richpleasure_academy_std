package academy.richpleasure.richpleasureacademy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;


public class ChapterAdapters extends RecyclerView.Adapter<ChapterAdapters.MyViewHolder>{

    private List<Chapters> chaptersList;
    private Context context;

    public ChapterAdapters(List<Chapters> chaptersList)
    {
        this.chaptersList = chaptersList;
    }

    @Override
    public ChapterAdapters.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_row,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChapterAdapters.MyViewHolder holder, int position) {
        context = holder.itemView.getContext();
        Chapters chapters = chaptersList.get(position);
        holder.progressBar.setProgress(chapters.getPercentCompleted());
        holder.chapter_name_std.setText(chapters.getChapterNameStd());
        holder.percent_completed.setText(chapters.getPercentCompleted().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent gotoLesson = new Intent (context,ChapterContentActivity.class);
                context.startActivity(gotoLesson);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chaptersList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView chapter_name_std,percent_completed,last_reviewed,chapter_id;
        public ProgressBar progressBar;

        public MyViewHolder(View itemView) {
            super(itemView);
            chapter_name_std = (TextView) itemView.findViewById(R.id.chapter_name_std);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            percent_completed = (TextView) itemView.findViewById(R.id.percent_completed);
        }
    }
}
