package fem.miw.upm.es.buscamusic.modelsTopTracks;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static fem.miw.upm.es.buscamusic.modelsTopTracks.TrackContract.tablaTopTracks;

class RepositorioTopTracks extends SQLiteOpenHelper {

    private static final String DB_NAME = tablaTopTracks.TABLE_NAME + ".db";
    private static final int DB_VERSION = 1;

    RepositorioTopTracks(Context contexto) {
        super(contexto, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String consultaSQL = "CREATE TABLE " + tablaTopTracks.TABLE_NAME + " ("
                + tablaTopTracks.COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tablaTopTracks.COL_NOMBRE  + " TEXT, "
                + tablaTopTracks.COL_IMAGEN     + " TEXT, "
                + tablaTopTracks.COL_ARTISTA     + " TEXT)";
        db.execSQL(consultaSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String consultaSQL = "DROP TABLE IF EXISTS " + tablaTopTracks.TABLE_NAME;
        db.execSQL(consultaSQL);
        onCreate(db);
    }

    public long add(String nombre, String imagen, String artista ) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(tablaTopTracks.COL_NOMBRE, nombre);
        valores.put(tablaTopTracks.COL_IMAGEN, imagen);
        valores.put(tablaTopTracks.COL_ARTISTA, artista);

        return db.insert(tablaTopTracks.TABLE_NAME, null, valores);
    }

    void deleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String consultaSQL = "DROP TABLE IF EXISTS " + tablaTopTracks.TABLE_NAME;
        db.execSQL(consultaSQL);
        onCreate(db);
    }
}
