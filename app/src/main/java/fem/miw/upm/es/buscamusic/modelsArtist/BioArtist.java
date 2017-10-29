
package fem.miw.upm.es.buscamusic.modelsArtist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BioArtist {

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("content")
    @Expose
    private String content;

    public String getSummary() {
        return summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BioArtist{" +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
