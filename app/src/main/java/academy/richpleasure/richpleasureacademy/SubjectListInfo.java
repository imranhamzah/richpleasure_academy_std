package academy.richpleasure.richpleasureacademy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubjectListInfo {

    @SerializedName("subject_title")
    @Expose
    private String subject_title;

    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    @SerializedName("total_tutors")
    @Expose
    private String total_tutors;

    public String getSubject_title() {
        return subject_title;
    }

    public void setSubject_title(String subject_title) {
        this.subject_title = subject_title;
    }

    public String getTotal_tutors() {
        return total_tutors;
    }

    public void setTotal_tutors(String total_tutors) {
        this.total_tutors = total_tutors;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
