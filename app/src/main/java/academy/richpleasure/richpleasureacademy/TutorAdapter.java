package academy.richpleasure.richpleasureacademy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class TutorAdapter extends RecyclerView.Adapter<TutorAdapter.MyViewHolder> {

    private List<Tutor> tutorList;
    private Context context;

    public ImageView tutorImage;
    public TextView tutorName;

    private TutorAdapterListener listener;
    private List<Tutor> tutorListFiltered;

    public TutorAdapter(List<Tutor> tutorList) {
        this.tutorList = tutorList;
    }

    public TutorAdapter(Context context, List<Tutor> tutorList, TutorAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.tutorList = tutorList;
        this.tutorListFiltered = tutorList;
    }

    public interface TutorAdapterListener {
        void onTutorSelected(Tutor tutor);
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
//                Intent gotoSubjectByTutotr = new Intent(context,SubjectsByTutor.class);
//                context.startActivity(gotoSubjectByTutotr);

                listener.onTutorSelected(tutorListFiltered.get(holder.getAdapterPosition()));
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

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    tutorListFiltered = tutorList;
                } else {
                    List<Tutor> filteredList = new ArrayList<>();
                    for (Tutor row : tutorList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTchFirstName().toLowerCase().contains(charString.toLowerCase()) || row.getTchImageUrl().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    tutorListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = tutorListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                tutorListFiltered = (ArrayList<Tutor>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
