package fem.miw.upm.es.buscamusic.modelsAlbum;

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

public class AlbumApi {

    private static final String API_URL = "http://ws.audioscrobbler.com/2.0/";
    private static final String API_KEY = "65a9df8546a88a427e63fe2522007ad4";
    private static final String API_FORMAT = "json";
    private static final String API_LENGUAJE = "es";

    private static final String LOG_TAG = "MiW";

    private RepositorioAlbum db_albums;

    private static final String METODO_INFOALBUM = "album.getinfo";

    private LastFMAPIService lastfmApiService;

    private Context context;
    private TextView tv;
    private ImageView iv;

    public AlbumApi(Context context, TextView tv, ImageView iv) {
        this.context = context;
        this.tv = tv;
        this.iv = iv;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        lastfmApiService = retrofit.create(LastFMAPIService.class);

    }

    public void buscarInfoAlbum(String artist, String album) {

        db_albums = new RepositorioAlbum(context);

        AlbumDetails albumAux = db_albums.get(artist, album);
        if (albumAux != null) {
            if (tv != null && iv != null) {
                Log.i(LOG_TAG, "El album ya esta en BBDD albums");
                tv.setText("Recuperado de BBDD: " + albumAux.getNombre() + "\n" + albumAux.getTracks());
                Picasso.with(context)
                        .load(albumAux.getImagen())
                        .into(iv);
            }
        } else {
            infoAlbumAPI(artist, album);
        }

    }

    void infoAlbumAPI(final String artist, final String album) {

        Call<Album> call_async = lastfmApiService.getAlbum(METODO_INFOALBUM, artist, album, API_KEY, API_FORMAT, API_LENGUAJE);

        call_async.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(@NonNull Call<Album> call, Response<Album> response) {

                Album respuestaAlbum = response.body();
                if (respuestaAlbum != null) {
                    if (respuestaAlbum.getAlbum() != null) {
                        if (tv != null && iv != null) {
                            tv.setText("Buscado en servidor:" + respuestaAlbum.getAlbum().getName() + "\n"
                                    + respuestaAlbum.getAlbum().getTracks());
                            if (!respuestaAlbum.getAlbum().getImage().get(3).getText().equals("")) {
                                Picasso.with(context).
                                        load(respuestaAlbum.getAlbum().getImage().get(3).getText())
                                        .into(iv);
                            }
                        }
                        addAlbumToBBDD(respuestaAlbum.getAlbum(), artist);
                        Log.i(LOG_TAG, "Respuesta album: " + respuestaAlbum.toString());
                    } else {
                        if (tv != null) {
                            tv.setText("No hay información");
                            iv.setImageResource(R.drawable.ic_busca_music_web);
                            Log.i(LOG_TAG, "No hay información del album");
                        }
                    }
                } else {
                    if (tv != null) {
                        tv.setText("No hay album");
                    }
                    Log.i(LOG_TAG, "No se ha recuperado el album");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Album> call, Throwable t) {
                Toast.makeText(
                        context,
                        "ERROR: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    private void addAlbumToBBDD(AlbumDetails album, String artist) {

        db_albums = new RepositorioAlbum(context);

        long id = db_albums.add(album.getName(), artist, album.getImage().get(3).getText(), album.getTracks().guardarNombresTracks());

        Log.i(LOG_TAG, "Album añadido: " + id);
    }

}
