
package fem.miw.upm.es.buscamusic.modelsTags;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import fem.miw.upm.es.buscamusic.modelsArtist.ArtistDetails;

public class Topartists {

    @SerializedName("artist")
    @Expose
    private List<ArtistDetails> artist = null;

    public List<ArtistDetails> getArtist() {
        return artist;
    }

    public void setArtist(List<ArtistDetails> artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Topartists{" +
                "artist=" + artist +
                '}';
    }
}
