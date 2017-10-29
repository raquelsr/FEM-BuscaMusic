
package fem.miw.upm.es.buscamusic.modelsArtist;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist {

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
}
