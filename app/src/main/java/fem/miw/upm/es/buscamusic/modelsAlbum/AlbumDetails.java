
package fem.miw.upm.es.buscamusic.modelsAlbum;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import fem.miw.upm.es.buscamusic.Image;
import fem.miw.upm.es.buscamusic.modelsTopTracks.Tracks;

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

    private int id;
    private String nombre;
    private String artista;
    private String imagen;
    private String listTracks;

    public AlbumDetails(int id, String nombre, String artista, String imagen, String listTracks) {
        this.id = id;
        this.nombre = nombre;
        this.artista = artista;
        this.imagen = imagen;
        this.listTracks = listTracks;
    }

    public int getId (){
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getListTracks() {
        return listTracks;
    }

    public void setListTracks(String listTracks) {
        this.listTracks = listTracks;
    }

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
