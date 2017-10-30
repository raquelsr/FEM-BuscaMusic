package fem.miw.upm.es.buscamusic.modelsArtist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import fem.miw.upm.es.buscamusic.LastFMAPIService;
import fem.miw.upm.es.buscamusic.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistApi {

    private static final String API_URL = "http://ws.audioscrobbler.com/2.0/";
    private static final String API_KEY = "65a9df8546a88a427e63fe2522007ad4";
    private static final String API_FORMAT = "json";
    private static final String API_LENGUAJE = "es";

    private static final String LOG_TAG = "MiW";

    private RepositorioArtist db_artist;

    private static final String METODO_INFOARTISTA = "artist.getinfo";

    private LastFMAPIService lastfmApiService;

    private Context context;
    private TextView tv;
    private ImageView iv;

    public ArtistApi(Context context, TextView tv, ImageView iv) {
        this.context = context;
        this.tv = tv;
        this.iv = iv;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        lastfmApiService = retrofit.create(LastFMAPIService.class);

    }

    public void buscarInfoArtist(String artist) {

        db_artist = new RepositorioArtist(context);

        ArtistDetails artistAux = db_artist.get(artist);
        if (artistAux != null) {
            if (tv != null && iv != null) {
                Log.i(LOG_TAG, "El album ya esta en BBDD artistas");
                tv.setText("Recuperado de BBDD : " + artistAux.getNombre() + "\n" + artistAux.getBio_contenido());
                Picasso.with(context)
                        .load(artistAux.getImagen())
                        .into(iv);
            }
        } else {
            infoArtistAPI(artist);
        }
    }

    void infoArtistAPI(String artist) {
        Call<Artist> call_async = lastfmApiService.getArtist(METODO_INFOARTISTA, artist, API_KEY, API_FORMAT, API_LENGUAJE);

        call_async.enqueue(new Callback<Artist>() {
            @Override
            public void onResponse(@NonNull Call<Artist> call, @NonNull Response<Artist> response) {
                Log.i(LOG_TAG, "RESPONSE: " + response.toString());
                Artist respuestaArtista = response.body();
                if (respuestaArtista != null) {
                    if (respuestaArtista.getArtist() != null) {
                        addArtistToBBDD(respuestaArtista.getArtist());
                        if (tv != null && iv != null) {
                            if (respuestaArtista.getArtist().toString() != null) {
                                tv.setText("Buscado en servidor: " + "\n" + respuestaArtista.getArtist().toString());
                            }
                            if (!respuestaArtista.getArtist().getImage().get(3).getText().equals("")) {
                                Picasso.with(context).
                                        load(respuestaArtista.getArtist().getImage().get(3).getText())
                                        .into(iv);
                            }
                        }
                    } else {
                        if (tv != null) {
                            tv.setText("No hay información");
                            iv.setImageResource(R.drawable.ic_busca_music_web);
                            Log.i(LOG_TAG, "No hay información del artista");
                        }
                    }

                } else {
                    if (tv != null) {
                        tv.setText("No hay artista");
                    }

                    Log.i(LOG_TAG, "No se ha recuperado el artista");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Artist> call, Throwable t) {
                Toast.makeText(
                        context,
                        "ERROR: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    private void addArtistToBBDD(ArtistDetails artist) {

        db_artist = new RepositorioArtist(context);

        long id = db_artist.add(artist.getName(), artist.getImage().get(3).getText(), artist.getBioArtist().getContent());

        Log.i(LOG_TAG, "Artista añadido: " + id);

    }
}
