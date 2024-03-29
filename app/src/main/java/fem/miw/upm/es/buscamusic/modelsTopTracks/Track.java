
package fem.miw.upm.es.buscamusic.modelsTopTracks;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import fem.miw.upm.es.buscamusic.Image;
import fem.miw.upm.es.buscamusic.modelsArtist.ArtistDetails;

public class Track {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("artist")
    @Expose
    private ArtistDetails artist;
    @SerializedName("image")
    @Expose
    private List<Image> image = null;

    private int id;
    private String nombre;
    private String imagen;
    private String artista;

    public Track(int id, String nombre, String imagen, String artista) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.artista = artista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public ArtistDetails getArtist() {
        return artist;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }


    public String getArtista() {
        return artista;
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

    @Override
    public String toString() {
        return "Track{" +
                "nombre='" + nombre + '\'' +
                ", artist=" + artista +
                '}';
    }

}
