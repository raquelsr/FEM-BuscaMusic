
package fem.miw.upm.es.buscamusic.modelsArtist;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BioArtist implements Parcelable {

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("content")
    @Expose
    private String content;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BioArtist{" +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                '}';
    }


    public BioArtist() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.summary);
        dest.writeString(this.content);
    }

    protected BioArtist(Parcel in) {
        this.summary = in.readString();
        this.content = in.readString();
    }

    public static final Creator<BioArtist> CREATOR = new Creator<BioArtist>() {
        @Override
        public BioArtist createFromParcel(Parcel source) {
            return new BioArtist(source);
        }

        @Override
        public BioArtist[] newArray(int size) {
            return new BioArtist[size];
        }
    };
}
