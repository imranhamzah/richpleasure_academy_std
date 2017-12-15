package academy.richpleasure.richpleasureacademy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tutor {
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTchFirstName() {
        return tchFirstName;
    }

    public void setTchFirstName(String tchFirstName) {
        this.tchFirstName = tchFirstName;
    }

    public String getTchLastName() {
        return tchLastName;
    }

    public void setTchLastName(String tchLastName) {
        this.tchLastName = tchLastName;
    }

    @SerializedName("teacher_id")
    @Expose
    private String teacherId;

    @SerializedName("tch_firstname")
    @Expose
    private String tchFirstName;

    @SerializedName("tch_lastname")
    @Expose
    private String tchLastName;

    public Tutor(String teacherId, String tchFirstName, String tchLastName) {
        this.teacherId = teacherId;
        this.tchFirstName = tchFirstName;
        this.tchLastName = tchLastName;
    }


}
