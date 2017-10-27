
package fem.miw.upm.es.buscamusic.modelsAlbum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("album")
    @Expose
    private AlbumDetails album;

    public AlbumDetails getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDetails album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Album{" +
                "album=" + album +
                '}';
    }
}
