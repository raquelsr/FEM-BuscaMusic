package fem.miw.upm.es.buscamusic.modelsTopTracks;

import android.provider.BaseColumns;

/**
 * Created by Raquel on 27/10/17.
 */

public class TrackContract {

    private TrackContract() {}

    public static abstract class tablaTopTracks implements BaseColumns {

        static final String TABLE_NAME   = "topTracks";

        static final String COL_ID       = _ID;
        static final String COL_NOMBRE   = "nombre";
        static final String COL_IMAGEN   = "imagen";
        static final String COL_ARTISTA  = "artista";
    }
}
