package fem.miw.upm.es.buscamusic;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import fem.miw.upm.es.buscamusic.modelsAlbum.AlbumApi;
import fem.miw.upm.es.buscamusic.modelsArtist.ArtistApi;
import fem.miw.upm.es.buscamusic.modelsTopTracks.TopTracksApi;


public class MainActivity extends AppCompatActivity {

    private EditText buscar_infoArtista;
    private TextView mostrar_text;
    private ImageView mostrar_img;
    private EditText buscar_infoAlbum;
    private RadioButton rb_artista;
    private RadioButton rb_album;
    private RadioButton rb_topTracks;

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

        buscar_infoArtista = (EditText) findViewById(R.id.edit_buscarinfo1);
        mostrar_text = (TextView) findViewById(R.id.txt_mostrarinfo);
        buscar_infoAlbum = (EditText) findViewById(R.id.edit_buscarinfoAlbum);
        mostrar_img = (ImageView) findViewById(R.id.iv_mostrarImagen);

        buscar_infoAlbum.setVisibility(View.INVISIBLE);

        rb_artista = (RadioButton) findViewById(R.id.rb_artista);
        rb_album = (RadioButton) findViewById(R.id.rb_album);
        rb_topTracks = (RadioButton) findViewById(R.id.rb_toptracks);
    }

    public void buscarInformacionAPI() {

        String artist = buscar_infoArtista.getText().toString();
        String album = buscar_infoAlbum.getText().toString();

        mostrar_text.setText("");

        if (rb_artista.isChecked()) {
            if (artist.equals("")) {
                Toast.makeText(this, R.string.introduceartista, Toast.LENGTH_SHORT).show();
            } else {
                ArtistApi a = new ArtistApi(this, mostrar_text, mostrar_img);
                a.buscarInfoArtist(artist);
            }
        } else if (rb_album.isChecked()) {
            if (artist.equals("")) {
                Toast.makeText(this, R.string.introduceartista, Toast.LENGTH_SHORT).show();
            } else if (album.equals("")) {
                Toast.makeText(this, R.string.introducealbum, Toast.LENGTH_SHORT).show();
            } else {
                AlbumApi a = new AlbumApi(this, mostrar_text, mostrar_img);
                a.buscarInfoAlbum(artist, album);
            }
        } else if (rb_topTracks.isChecked()) {
            TopTracksApi a = new TopTracksApi(this, mostrar_text);
            a.buscarTopTracks();
        }
    }

    public void modificarEdits(View v) {
        if (rb_album.isChecked()) {
            buscar_infoAlbum.setVisibility(View.VISIBLE);
            buscar_infoArtista.setVisibility(View.VISIBLE);
        } else if (rb_artista.isChecked()) {
            buscar_infoAlbum.setVisibility(View.INVISIBLE);
            buscar_infoArtista.setVisibility(View.VISIBLE);
            buscar_infoArtista.setHint(getResources().getString(R.string.introduceartista));
        } else {
            buscar_infoAlbum.setVisibility(View.INVISIBLE);
            buscar_infoArtista.setVisibility(View.INVISIBLE);
        }
    }
}
