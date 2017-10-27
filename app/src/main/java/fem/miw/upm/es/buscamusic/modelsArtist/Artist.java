
package fem.miw.upm.es.buscamusic.modelsArtist;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist implements Parcelable {

    @SerializedName("artist")
    @Expose
    private ArtistDetails artist;

    public ArtistDetails getArtist() {
        return artist;
    }

    public void setArtist(ArtistDetails artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artist=" + artist +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.artist, flags);
    }

    public Artist() {
    }

    protected Artist(Parcel in) {
        this.artist = in.readParcelable(ArtistDetails.class.getClassLoader());
    }

    public static final Parcelable.Creator<Artist> CREATOR = new Parcelable.Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel source) {
            return new Artist(source);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };
}
