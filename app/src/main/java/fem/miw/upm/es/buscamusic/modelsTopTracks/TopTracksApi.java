package fem.miw.upm.es.buscamusic.modelsTopTracks;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fem.miw.upm.es.buscamusic.LastFMAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopTracksApi {

    private static final String API_URL = "http://ws.audioscrobbler.com/2.0/";
    private static final String API_KEY = "65a9df8546a88a427e63fe2522007ad4";
    private static final String API_FORMAT = "json";
    private static final String API_LENGUAJE = "es";

    private static final String LOG_TAG = "MiW";

    private RepositorioTopTracks db_TopTracks;

    private static final String METODO_TOPTRACKS = "chart.gettoptracks";

    private LastFMAPIService lastfmApiService;

    private Context context;
    private TextView tv;

    public TopTracksApi(Context context, TextView tv){
        this.context = context;
        this.tv = tv;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        lastfmApiService = retrofit.create(LastFMAPIService.class);

    }

    public void buscarTopTracks () {

        db_TopTracks = new RepositorioTopTracks(context);

        db_TopTracks.delete();

        infoTopTrackAPI();

    }

    public void infoTopTrackAPI() {

        Call<TopTracks> call_async = lastfmApiService.getTopTracks(METODO_TOPTRACKS, API_KEY, API_FORMAT, API_LENGUAJE);

        call_async.enqueue(new Callback<TopTracks>() {
            @Override
            public void onResponse(Call<TopTracks> call, Response<TopTracks> response) {
                TopTracks respuestaTopTracks = response.body();
                if (respuestaTopTracks != null) {
                    if (tv!=null){
                        tv.setText(respuestaTopTracks.getTracks().guardarNombresTracks() + "\n");
                    }
                    addTracksToBBDD(respuestaTopTracks.getTracks().getTrack());
                    Log.i(LOG_TAG, "Respuesta TRACKS: " + respuestaTopTracks.toString());
                } else {
                    tv.setText("No hay tracks");
                    Log.i(LOG_TAG, "No se ha recuperado el tracks");
                }
            }

            @Override
            public void onFailure(Call<TopTracks> call, Throwable t) {
                Toast.makeText(
                        context,
                        "ERROR: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    private void addTracksToBBDD(List<Track> tracks) {

        db_TopTracks = new RepositorioTopTracks(context);

        for (Track t: tracks){
            db_TopTracks.add(t.getName(), t.getImage().get(2).getText(), t.getArtist().getName());
        }

        Log.i(LOG_TAG, "TopTracks añadidos ");

    }
}
