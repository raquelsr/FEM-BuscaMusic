package fem.miw.upm.es.buscamusic;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import fem.miw.upm.es.buscamusic.modelsAlbum.Album;
import fem.miw.upm.es.buscamusic.modelsAlbum.AlbumDetails;
import fem.miw.upm.es.buscamusic.modelsAlbum.RepositorioAlbum;
import fem.miw.upm.es.buscamusic.modelsArtist.Artist;
import fem.miw.upm.es.buscamusic.modelsArtist.ArtistApi;
import fem.miw.upm.es.buscamusic.modelsArtist.ArtistDetails;
import fem.miw.upm.es.buscamusic.modelsArtist.RepositorioArtist;
import fem.miw.upm.es.buscamusic.modelsTags.Tag;
import fem.miw.upm.es.buscamusic.modelsTopTracks.RepositorioTopTracks;
import fem.miw.upm.es.buscamusic.modelsTopTracks.TopTracks;
import fem.miw.upm.es.buscamusic.modelsTopTracks.Track;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String API_URL = "http://ws.audioscrobbler.com/2.0/";
    private static final String API_KEY = "65a9df8546a88a427e63fe2522007ad4";
    private static final String API_FORMAT = "json";
    private static final String API_LENGUAJE = "es";

    private static final String LOG_TAG = "MiW";

    private EditText buscar_infoArtista;
    private TextView mostrar_text;
    private ImageView mostrar_img;
    private EditText buscar_infoAlbum;
    private RadioButton rb_artista;
    private RadioButton rb_album;
    private RadioButton rb_topTracks;
    private RadioButton rb_tags;

    private RepositorioArtist db_artist;
    private RepositorioAlbum db_albums;
    private RepositorioTopTracks db_TopTracks;


    private static final String METODO_INFOARTISTA = "artist.getinfo";
    private static final String METODO_INFOALBUM = "album.getinfo";
    private static final String METODO_TOPTRACKS = "chart.gettoptracks";
    private static final String METODO_TAGS = "tag.gettopartists";

    private LastFMAPIService lastfmApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarInformacionAPI();
            }
        });

        buscar_infoArtista = (EditText) findViewById(R.id.edit_buscarinfoArtista);
        mostrar_text = (TextView) findViewById(R.id.txt_mostrarinfo);
        buscar_infoAlbum = (EditText) findViewById(R.id.edit_buscarinfoAlbum);
        mostrar_img = (ImageView) findViewById(R.id.iv_mostrarimagen);


        rb_artista = (RadioButton) findViewById(R.id.rb_artista);
        rb_album = (RadioButton) findViewById(R.id.rb_album);
        rb_topTracks = (RadioButton) findViewById(R.id.rb_toptracks);
        rb_tags = (RadioButton) findViewById(R.id.rb_tags);

        if (rb_artista.isChecked()) {
            buscar_infoAlbum.setVisibility(View.INVISIBLE);
        } else if (rb_album.isChecked()) {
            buscar_infoAlbum.setVisibility(View.VISIBLE);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        lastfmApiService = retrofit.create(LastFMAPIService.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buscarInformacionAPI() {

        String artist = buscar_infoArtista.getText().toString();
        String album = buscar_infoAlbum.getText().toString();

        if (rb_artista.isChecked()) {
           // buscarInfoArtist(artist);
            ArtistApi a = new ArtistApi(this,mostrar_text,mostrar_img);
            a.buscarInfoArtist(artist);
        } else if (rb_album.isChecked()) {
            buscarInfoAlbum(artist, album);
        } else if (rb_topTracks.isChecked()) {
            buscarTopTracks(artist);
        } else if (rb_tags.isChecked()) {
          //  buscarTopArtistPorTags(artist);
        }
    }

    // ALBUMMMMMMMM

    private void buscarInfoAlbum(String artist, String album) {

        db_albums = new RepositorioAlbum(getApplicationContext());

        AlbumDetails albumAux = db_albums.get(artist, album);
        if (albumAux != null) {
            mostrar_text.setText("YA ESTA EN BBDD" + albumAux.getNombre() + albumAux.getImagen() + albumAux.getArtista());
        } else {
            infoAlbumAPI(artist, album);
        }

    }

    public void infoAlbumAPI(String artist, final String album) {

        Call<Album> call_async = lastfmApiService.getAlbum(METODO_INFOALBUM, artist, album, API_KEY, API_FORMAT, API_LENGUAJE);

        call_async.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                Log.i(LOG_TAG, "RESPONSE " + response.toString());
                Album respuestaAlbum = response.body();
                if (respuestaAlbum != null) {
                    mostrar_text.setText(respuestaAlbum.getAlbum().toString() + "\n");
                    Picasso.with(getApplicationContext()).
                            load(respuestaAlbum.getAlbum().getImage().get(3).getText())
                            .into(mostrar_img);

                    addAlbumToBBDD(respuestaAlbum.getAlbum());

                    Log.i(LOG_TAG, "Respuesta artista: " + respuestaAlbum.toString());
                } else {
                    mostrar_text.setText("No hay artista");
                    Log.i(LOG_TAG, "No se ha recuperado el artista");
                }
            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                Toast.makeText(
                        getApplicationContext(),
                        "ERROR: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    private void addAlbumToBBDD(AlbumDetails album) {

        db_albums = new RepositorioAlbum(getApplicationContext());

        long id = db_albums.add(album.getName(), buscar_infoArtista.getText().toString(), album.getImage().get(3).getText(), album.getTracks().getTrack().toString(),
                album.getTracks().getTrack().toString());

        Log.i(LOG_TAG, "Album añadido: " + id);

    }


    // TOP TRACKSSSS

    private void buscarTopTracks (String limit) {

        db_TopTracks = new RepositorioTopTracks(getApplicationContext());

        List<Track> listTracks = db_TopTracks.getAll(limit);
        if (!listTracks.isEmpty()) {
            mostrar_text.setText("YA ESTA EN BBDD" + listTracks.size() + listTracks.toString());
        } else {
            infoTopTrackAPI();
        }

    }

    public void infoTopTrackAPI() {

        Call<TopTracks> call_async = lastfmApiService.getTopTracks(METODO_TOPTRACKS, API_KEY, API_FORMAT, API_LENGUAJE);

        call_async.enqueue(new Callback<TopTracks>() {
            @Override
            public void onResponse(Call<TopTracks> call, Response<TopTracks> response) {
                Log.i(LOG_TAG, "Entra por aqui");
                Log.i(LOG_TAG, "RESPONSE " + response.toString());
                TopTracks respuestaTopTracks = response.body();
                if (respuestaTopTracks != null) {
                    mostrar_text.setText(respuestaTopTracks.toString() + "\n");

                    addTracksToBBDD(respuestaTopTracks.getTracks().getTrack());
                    Log.i(LOG_TAG, "Respuesta artista: " + respuestaTopTracks.toString());
                } else {
                    mostrar_text.setText("No hay artista");
                    Log.i(LOG_TAG, "No se ha recuperado el artista");
                }
            }

            @Override
            public void onFailure(Call<TopTracks> call, Throwable t) {
                Toast.makeText(
                        getApplicationContext(),
                        "ERROR: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                Log.e(LOG_TAG, t.getMessage());
                Log.e(LOG_TAG, "ERROR POR QUI");
            }
        });
    }

    private void addTracksToBBDD(List<Track> tracks) {

        db_TopTracks = new RepositorioTopTracks(getApplicationContext());

        for (Track t: tracks){
            db_TopTracks.add(t.getName(), t.getImagen(), t.getArtista());
        }

        Log.i(LOG_TAG, "TopTracks añadidos ");

    }

}
