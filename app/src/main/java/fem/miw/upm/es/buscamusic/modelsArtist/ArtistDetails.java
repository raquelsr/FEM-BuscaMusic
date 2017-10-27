
package fem.miw.upm.es.buscamusic.modelsArtist;

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
        @SerializedName("tags")
        @Expose
        private Tags tags;
        @SerializedName("bio")
        @Expose
        private Bio bio;

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

        public Tags getTags() {
            return tags;
        }

        public void setTags(Tags tags) {
            this.tags = tags;
        }

        public Bio getBio() {
            return bio;
        }

        public void setBio(Bio bio) {
            this.bio = bio;
        }

    @Override
    public String toString() {
        return "ArtistDetails{" +
                "name='" + name + '\'' +
                ", image=" + image +
                ", tags=" + tags +
                ", bio=" + bio +
                '}';
    }
}
