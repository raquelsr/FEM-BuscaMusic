
package fem.miw.upm.es.buscamusic.modelsArtist;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import fem.miw.upm.es.buscamusic.Image;

public class ArtistDetails implements Parcelable {

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
    private String bio_resumen;
    private String bio_contenido;
    private int puntuacion;
    private String comentario;

    public ArtistDetails(int id, String nombre, String imagen, String bio_resumen, String bio_contenido, int puntuacion) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.bio_resumen = bio_resumen;
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

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public BioArtist getBioArtist() {
        return bioArtist;
    }

    public void setBioArtist(BioArtist bioArtist) {
        this.bioArtist = bioArtist;
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

    public String getBio_resumen() {
        return bio_resumen;
    }

    public void setBio_resumen(String bio_resumen) {
        this.bio_resumen = bio_resumen;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "ArtistDetails{" +
                "name='" + name + '\'' +
                ", image=" + image +
                ", bioArtist=" + bioArtist +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeList(this.image);
        dest.writeParcelable(this.bioArtist, flags);
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.imagen);
        dest.writeString(this.bio_resumen);
        dest.writeString(this.bio_contenido);
    }

    protected ArtistDetails(Parcel in) {
        this.name = in.readString();
        this.image = new ArrayList<Image>();
        in.readList(this.image, Image.class.getClassLoader());
        this.bioArtist = in.readParcelable(BioArtist.class.getClassLoader());
        this.id = in.readInt();
        this.nombre = in.readString();
        this.imagen = in.readString();
        this.bio_resumen = in.readString();
        this.bio_contenido = in.readString();
    }

    public static final Creator<ArtistDetails> CREATOR = new Creator<ArtistDetails>() {
        @Override
        public ArtistDetails createFromParcel(Parcel source) {
            return new ArtistDetails(source);
        }

        @Override
        public ArtistDetails[] newArray(int size) {
            return new ArtistDetails[size];
        }
    };
}
