package academy.richpleasure.richpleasureacademy.infinite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class InfiniteTutorInfo {
    @SerializedName("tutorName")
    @Expose
    private String tutorName;

    @SerializedName("tutorSubjects")
    @Expose
    private String tutorSubjects;

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getTutorSubjects() {
        return tutorSubjects;
    }

    public void setTutorSubjects(String tutorSubjects) {
        this.tutorSubjects = tutorSubjects;
    }
}
