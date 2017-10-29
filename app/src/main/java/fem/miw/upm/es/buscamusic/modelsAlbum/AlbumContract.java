package fem.miw.upm.es.buscamusic.modelsAlbum;

import android.provider.BaseColumns;


public class AlbumContract {

    private AlbumContract() {}

    public static abstract class tablaAlbum implements BaseColumns {

        static final String TABLE_NAME   = "albums";

        static final String COL_ID       = _ID;
        static final String COL_NOMBRE   = "nombre";
        static final String COL_ARTISTA  = "artista";
        static final String COL_IMAGEN   = "imagen";
        static final String COL_TRACKS  = "tracks";
    }

}
