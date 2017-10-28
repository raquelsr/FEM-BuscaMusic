package fem.miw.upm.es.buscamusic.modelsArtist;

import android.provider.BaseColumns;

public class ArtistContract {

    private ArtistContract() {}

    public static abstract class tablaArtista implements BaseColumns {

        static final String TABLE_NAME   = "artistas";

        static final String COL_ID       = _ID;
        static final String COL_NOMBRE   = "nombre";
        static final String COL_IMAGEN   = "imagen";
        static final String COL_BIO_RESUMEN   = "bio_resumen";
        static final String COL_BIO_CONTENIDO = "bio_contenido";
    }
}
