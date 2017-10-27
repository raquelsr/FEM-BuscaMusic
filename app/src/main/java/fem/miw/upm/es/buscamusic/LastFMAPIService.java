package fem.miw.upm.es.buscamusic;

import java.util.List;

import fem.miw.upm.es.buscamusic.modelsAlbum.Album;
import fem.miw.upm.es.buscamusic.modelsArtist.Artist;
import fem.miw.upm.es.buscamusic.modelsTags.Tag;
import fem.miw.upm.es.buscamusic.modelsTags.Topartists;

import fem.miw.upm.es.buscamusic.modelsTopTracks.TopTracks;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFMAPIService {

    @GET("?")
    Call<Artist> getArtist (@Query("method") String method, @Query("artist") String artist,
                           @Query("api_key") String api_key, @Query ("format") String format, @Query("lang") String lenguaje);

    @GET("?")
    Call<Album> getAlbum (@Query("method") String method, @Query("artist") String artist, @Query("album") String album,
                          @Query("api_key") String api_key, @Query ("format") String format, @Query("lang") String lenguaje);

    @GET("?")
    Call<Tag> getTracks (@Query("method") String method, @Query("tag") String tag,
                         @Query("api_key") String api_key, @Query ("format") String format, @Query("lang") String lenguaje);

    @GET("?")
    Call<TopTracks> getTopTracks (@Query("method") String method,  @Query("api_key") String api_key,
                                  @Query ("format") String format, @Query("lang") String lenguaje);

}
