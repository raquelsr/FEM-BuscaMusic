
package fem.miw.upm.es.buscamusic.modelsTags;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tag {

    @SerializedName("topartists")
    @Expose
    private Topartists topartists;

    public Topartists getTopartists() {
        return topartists;
    }

    public void setTopartists(Topartists topartists) {
        this.topartists = topartists;
    }

    @Override
    public String toString() {
        return "Tag_Artist{" +
                "topartists=" + topartists +
                '}';
    }
}
