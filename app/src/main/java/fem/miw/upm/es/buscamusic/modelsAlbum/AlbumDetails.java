
package fem.miw.upm.es.buscamusic.modelsAlbum;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import fem.miw.upm.es.buscamusic.Image;

public class AlbumDetails {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private List<Image> image = null;
    @SerializedName("tracks")
    @Expose
    private Tracks tracks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }


    @Override
    public String toString() {
        return "AlbumDetails{" +
                "name='" + name + '\'' +
                ", image=" + image +
                ", tracks=" + tracks +
                '}';
    }
}
