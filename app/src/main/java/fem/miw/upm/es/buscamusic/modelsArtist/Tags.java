
package fem.miw.upm.es.buscamusic.modelsArtist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tags {

    @SerializedName("tagArtist")
    @Expose
    private List<Tag_Artist> tagArtist = null;

    public List<Tag_Artist> getTagArtist() {
        return tagArtist;
    }

    public void setTagArtist(List<Tag_Artist> tagArtist) {
        this.tagArtist = tagArtist;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "tagArtist=" + tagArtist +
                '}';
    }
}
