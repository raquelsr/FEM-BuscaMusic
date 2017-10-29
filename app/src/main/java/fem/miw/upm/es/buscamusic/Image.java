
package fem.miw.upm.es.buscamusic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("#text")
    @Expose
    private String text;

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Image{" +
                "text='" + text + '\'' +
                '}';
    }
}
