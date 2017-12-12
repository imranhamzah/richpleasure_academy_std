package academy.richpleasure.richpleasureacademy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subjects {

    @SerializedName("scode_id")
    @Expose
    private Integer scodeId;

    @SerializedName("short_name_std")
    @Expose
    private String shortNameStd;

    @SerializedName("subject_name_std")
    @Expose
    private String subjectNameStd;

    @SerializedName("colour_code")
    @Expose
    private String colourCode;

    @SerializedName("icon_filename")
    @Expose
    private String iconFilename;

    public Integer getScodeId() {
        return scodeId;
    }

    public void setScodeId(Integer scodeId) {
        this.scodeId = scodeId;
    }

    public String getShortNameStd() {
        return shortNameStd;
    }

    public void setShortNameStd(String shortNameStd) {
        this.shortNameStd = shortNameStd;
    }

    public String getColourCode() {
        return colourCode;
    }

    public void setColourCode(String colourCode) {
        this.colourCode = colourCode;
    }

    public String getIconFilename() {
        return iconFilename;
    }

    public void setIconFilename(String iconFilename) {
        this.iconFilename = iconFilename;
    }

    public String getSubjectNameStd() {
        return subjectNameStd;
    }

    public void setSubjectNameStd(String subjectNameStd) {
        this.subjectNameStd = subjectNameStd;
    }
}
