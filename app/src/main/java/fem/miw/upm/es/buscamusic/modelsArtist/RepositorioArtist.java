package fem.miw.upm.es.buscamusic.modelsArtist;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static fem.miw.upm.es.buscamusic.modelsArtist.ArtistContract.tablaArtista;

public class RepositorioArtist extends SQLiteOpenHelper {

    private static final String DB_NAME = tablaArtista.TABLE_NAME + ".db";
    private static final int DB_VERSION = 1;

    public RepositorioArtist(Context contexto) {
        super(contexto, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String consultaSQL = "CREATE TABLE " + tablaArtista.TABLE_NAME + " ("
                + tablaArtista.COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tablaArtista.COL_NOMBRE  + " TEXT, "
                + tablaArtista.COL_IMAGEN     + " TEXT, "
                + tablaArtista.COL_BIO_RESUMEN     + " TEXT, "
                + tablaArtista.COL_BIO_CONTENIDO   + " TEXT)";
        db.execSQL(consultaSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String consultaSQL = "DROP TABLE IF EXISTS " + tablaArtista.TABLE_NAME;
        db.execSQL(consultaSQL);
        onCreate(db);
    }

    public long add(String nombre, String imagen, String bio_resumen, String bio_contenido ) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(tablaArtista.COL_NOMBRE, nombre);
        valores.put(tablaArtista.COL_IMAGEN, imagen);
        valores.put(tablaArtista.COL_BIO_RESUMEN, bio_resumen);
        valores.put(tablaArtista.COL_BIO_CONTENIDO, bio_contenido);

        return db.insert(tablaArtista.TABLE_NAME, null, valores);
    }


    public ArtistDetails get(String nombre) {
        String consultaSQL = "SELECT * FROM " + tablaArtista.TABLE_NAME +
                " WHERE " + tablaArtista.COL_NOMBRE + " LIKE " + "\"" + nombre + "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);
        ArtistDetails artist=null;

        if (cursor.moveToFirst()) {
                 artist = new ArtistDetails(
                        cursor.getInt(cursor.getColumnIndex(tablaArtista.COL_ID)),
                        cursor.getString(cursor.getColumnIndex(tablaArtista.COL_NOMBRE)),
                        cursor.getString(cursor.getColumnIndex(tablaArtista.COL_IMAGEN)),
                        cursor.getString(cursor.getColumnIndex(tablaArtista.COL_BIO_RESUMEN)),
                        cursor.getString(cursor.getColumnIndex(tablaArtista.COL_BIO_CONTENIDO))
                );
        }

        cursor.close();
        db.close();

        return artist;
    }

    public ArrayList<ArtistDetails> getAll() {
        String consultaSQL = "SELECT * FROM " + tablaArtista.TABLE_NAME;
        ArrayList<ArtistDetails> listArtist = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                ArtistDetails artist = new ArtistDetails(
                        cursor.getInt(cursor.getColumnIndex(tablaArtista.COL_ID)),
                        cursor.getString(cursor.getColumnIndex(tablaArtista.COL_NOMBRE)),
                        cursor.getString(cursor.getColumnIndex(tablaArtista.COL_IMAGEN)),
                        cursor.getString(cursor.getColumnIndex(tablaArtista.COL_BIO_RESUMEN)),
                        cursor.getString(cursor.getColumnIndex(tablaArtista.COL_BIO_CONTENIDO))
                        );

                listArtist.add(artist);
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return listArtist;
    }
}
