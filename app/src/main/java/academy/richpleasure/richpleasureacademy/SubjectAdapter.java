package academy.richpleasure.richpleasureacademy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mindorks.placeholderview.PlaceHolderView;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder>{

    private List<Subjects> subjectsList;
    private Context context;

    public SubjectAdapter(List<Subjects> subjectsList) {
        this.subjectsList = subjectsList;
    }

    @Override
    public SubjectAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SubjectAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
