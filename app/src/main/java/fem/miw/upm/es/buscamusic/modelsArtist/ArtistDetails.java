
package fem.miw.upm.es.buscamusic.modelsArtist;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import fem.miw.upm.es.buscamusic.Image;

public class ArtistDetails {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private List<Image> image = null;
    @SerializedName("bio")
    @Expose
    private BioArtist bioArtist;

    private int id;
    private String nombre;
    private String imagen;
    private String bio_contenido;
    private int puntuacion;

    public ArtistDetails(int id, String nombre, String imagen, String bio_contenido, int puntuacion) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.bio_contenido = bio_contenido;
        this.puntuacion = puntuacion;
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

    public BioArtist getBioArtist() {
        return bioArtist;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getBio_contenido() {
        return bio_contenido;
    }

    public void setBio_contenido(String bio_contenido) {
        this.bio_contenido = bio_contenido;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return "ArtistDetails{" +
                "name='" + name + '\'' +
                ", image=" + image +
                ", bioArtist=" + bioArtist +
                '}';
    }
}
