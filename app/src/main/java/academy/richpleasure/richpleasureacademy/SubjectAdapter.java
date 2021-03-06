package academy.richpleasure.richpleasureacademy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder>{

    private List<Subjects> subjectsList;
    private Context context;

    public SubjectAdapter(List<Subjects> subjectsList) {
        this.subjectsList = subjectsList;
    }

    @Override
    public SubjectAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_row,parent,false);
        return new SubjectAdapter.MyViewHolder(itemView);
    }

@Override
    public void onBindViewHolder(SubjectAdapter.MyViewHolder holder, int position) {
        context = holder.itemView.getContext();
        Subjects subjects = subjectsList.get(position);

        String iconFilename = subjects.getIconFilename();
        if (iconFilename.indexOf(".") > 0)
        iconFilename = iconFilename.substring(0, iconFilename.lastIndexOf("."));

        holder.iconFilename.setImageResource(context.getResources().getIdentifier(iconFilename, "drawable", context.getPackageName()));
        holder.subjectNameStd.setText(subjects.getSubjectNameStd());
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent gotoChapter = new Intent (context,ChapterActivity.class);
                gotoChapter.putExtra("subjectNameStd",subjects.getSubjectNameStd());
                context.startActivity(gotoChapter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public ImageView iconFilename;
        public TextView subjectNameStd,colourCode,shortNameStd,scode_id;
        public MyViewHolder(View itemView) {
            super(itemView);
            iconFilename = (ImageView) itemView.findViewById(R.id.iconFilename);
            subjectNameStd = (TextView) itemView.findViewById(R.id.subjectNameStd);
        }
    }
}
