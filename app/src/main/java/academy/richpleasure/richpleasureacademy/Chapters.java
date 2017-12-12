package academy.richpleasure.richpleasureacademy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Imran on 11/28/2017.
 */

public class Chapters {

    @SerializedName("chapter_id")
    @Expose
    private Integer chapterId;

    @SerializedName("percent_completed")
    @Expose
    private Integer percentCompleted;

    @SerializedName("chapter_name_std")
    @Expose
    private String chapterNameStd;

    @SerializedName("last_reviewed")
    @Expose
    private String lastReviewed;


    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getPercentCompleted() {
        return percentCompleted;
    }

    public void setPercentCompleted(Integer percentCompleted) {
        this.percentCompleted = percentCompleted;
    }

    public String getChapterNameStd() {
        return chapterNameStd;
    }

    public void setChapterNameStd(String chapterNameStd) {
        this.chapterNameStd = chapterNameStd;
    }

    public String getLastReviewed() {
        return lastReviewed;
    }

    public void setLastReviewed(String lastReviewed) {
        this.lastReviewed = lastReviewed;
    }



}
