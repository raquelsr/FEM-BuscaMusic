package fem.miw.upm.es.buscamusic.modelsArtist;

import android.provider.BaseColumns;

class ArtistContract {

    private ArtistContract() {}

    static abstract class tablaArtista implements BaseColumns {

        static final String TABLE_NAME   = "artistas";

        static final String COL_ID       = _ID;
        static final String COL_NOMBRE   = "nombre";
        static final String COL_IMAGEN   = "imagen";
        static final String COL_BIO_CONTENIDO = "bio_contenido";
        static final String COL_PUNTUACION = "puntuacion";
    }
}
