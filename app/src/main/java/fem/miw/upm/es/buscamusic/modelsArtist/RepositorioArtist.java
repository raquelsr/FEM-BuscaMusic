package fem.miw.upm.es.buscamusic.modelsArtist;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static fem.miw.upm.es.buscamusic.modelsArtist.ArtistContract.tablaArtista;

class RepositorioArtist extends SQLiteOpenHelper {

    private static final String DB_NAME = tablaArtista.TABLE_NAME + ".db";
    private static final int DB_VERSION = 1;

    RepositorioArtist(Context contexto) {
        super(contexto, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String consultaSQL = "CREATE TABLE " + tablaArtista.TABLE_NAME + " ("
                + tablaArtista.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tablaArtista.COL_NOMBRE + " TEXT, "
                + tablaArtista.COL_IMAGEN + " TEXT, "
                + tablaArtista.COL_BIO_CONTENIDO + " TEXT, "
                + tablaArtista.COL_PUNTUACION + " INTEGER)";
        db.execSQL(consultaSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String consultaSQL = "DROP TABLE IF EXISTS " + tablaArtista.TABLE_NAME;
        db.execSQL(consultaSQL);
        onCreate(db);
    }

    public long add(String nombre, String imagen, String bio_contenido) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(tablaArtista.COL_NOMBRE, nombre);
        valores.put(tablaArtista.COL_IMAGEN, imagen);
        valores.put(tablaArtista.COL_BIO_CONTENIDO, bio_contenido);
        valores.put(tablaArtista.COL_PUNTUACION, -1);

        return db.insert(tablaArtista.TABLE_NAME, null, valores);
    }


    ArtistDetails get(String nombre) {
        String consultaSQL = "SELECT * FROM " + tablaArtista.TABLE_NAME +
                " WHERE " + tablaArtista.COL_NOMBRE + " LIKE " + "\"" + nombre + "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);
        ArtistDetails artist = null;

        if (cursor.moveToFirst()) {
            artist = new ArtistDetails(
                    cursor.getInt(cursor.getColumnIndex(tablaArtista.COL_ID)),
                    cursor.getString(cursor.getColumnIndex(tablaArtista.COL_NOMBRE)),
                    cursor.getString(cursor.getColumnIndex(tablaArtista.COL_IMAGEN)),
                    cursor.getString(cursor.getColumnIndex(tablaArtista.COL_BIO_CONTENIDO)),
                    cursor.getInt(cursor.getColumnIndex(tablaArtista.COL_PUNTUACION))
            );
        }

        cursor.close();
        db.close();

        return artist;
    }
}
