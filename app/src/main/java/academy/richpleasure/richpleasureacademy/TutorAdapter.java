package academy.richpleasure.richpleasureacademy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class TutorAdapter extends RecyclerView.Adapter<TutorAdapter.MyViewHolder> {

    private List<Tutor> tutorList;
    private Context context;

    public ImageView tutorImage;
    public TextView tutorName;

    public TutorAdapter(List<Tutor> tutorList) {
        this.tutorList = tutorList;
    }

    @Override
    public TutorAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tutor_row_layout,parent,false);
        return new TutorAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TutorAdapter.MyViewHolder holder, int position) {
        context = holder.itemView.getContext();
        Tutor tutor = tutorList.get(position);

        Glide.with(context).load(tutor.getTchImageUrl()).into(tutorImage);
        tutorName.setText(tutor.getTchFirstName());


        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent gotoSubjectByTutotr = new Intent(context,SubjectsByTutor.class);
                context.startActivity(gotoSubjectByTutotr);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.tutorList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
            tutorImage = (ImageView) itemView.findViewById(R.id.tutorImage);
            tutorName = (TextView) itemView.findViewById(R.id.tutorName);
        }
    }
}
