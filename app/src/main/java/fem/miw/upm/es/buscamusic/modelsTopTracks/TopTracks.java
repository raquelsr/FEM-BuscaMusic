
package fem.miw.upm.es.buscamusic.modelsTopTracks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopTracks {

    @SerializedName("tracks")
    @Expose
    private Tracks tracks;

    public Tracks getTracks() {
        return tracks;
    }

    @Override
    public String toString() {
        return "TopTracks{" +
                "tracks=" + tracks +
                '}';
    }
}
