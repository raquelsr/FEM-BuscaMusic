package fem.miw.upm.es.buscamusic.modelsAlbum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import fem.miw.upm.es.buscamusic.modelsArtist.ArtistContract;
import fem.miw.upm.es.buscamusic.modelsArtist.ArtistDetails;

import static fem.miw.upm.es.buscamusic.modelsAlbum.AlbumContract.tablaAlbum;

public class RepositorioAlbum extends SQLiteOpenHelper {

    private static final String DB_NAME = tablaAlbum.TABLE_NAME + ".db";
    private static final int DB_VERSION = 1;

    public RepositorioAlbum(Context contexto) {
        super(contexto, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String consultaSQL = "CREATE TABLE " + tablaAlbum.TABLE_NAME + " ("
                + tablaAlbum.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tablaAlbum.COL_NOMBRE + " TEXT, "
                + tablaAlbum.COL_ARTISTA + " TEXT, "
                + tablaAlbum.COL_IMAGEN + " TEXT, "
                + tablaAlbum.COL_TRACKS + " TEXT)";
        db.execSQL(consultaSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String consultaSQL = "DROP TABLE IF EXISTS " + tablaAlbum.TABLE_NAME;
        db.execSQL(consultaSQL);
        onCreate(db);
    }

    public long add(String nombre, String artista, String imagen, String tracks) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(tablaAlbum.COL_NOMBRE, nombre);
        valores.put(tablaAlbum.COL_ARTISTA, artista);
        valores.put(tablaAlbum.COL_IMAGEN, imagen);
        valores.put(tablaAlbum.COL_TRACKS, tracks);

        return db.insert(tablaAlbum.TABLE_NAME, null, valores);
    }


    public AlbumDetails get(String artista, String nombre) {
        String consultaSQL = "SELECT * FROM " + tablaAlbum.TABLE_NAME +
                " WHERE " + tablaAlbum.COL_NOMBRE + " LIKE " + "\"" + nombre + "\"" + " AND " +
                tablaAlbum.COL_ARTISTA + " LIKE " + "\"" + artista + "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);
        AlbumDetails album = null;

        if (cursor.moveToFirst()) {
            album = new AlbumDetails(
                    cursor.getInt(cursor.getColumnIndex(tablaAlbum.COL_ID)),
                    cursor.getString(cursor.getColumnIndex(tablaAlbum.COL_NOMBRE)),
                    cursor.getString(cursor.getColumnIndex(tablaAlbum.COL_ARTISTA)),
                    cursor.getString(cursor.getColumnIndex(tablaAlbum.COL_IMAGEN)),
                    cursor.getString(cursor.getColumnIndex(tablaAlbum.COL_TRACKS))
            );
        }

        cursor.close();
        db.close();

        return album;
    }

    public ArrayList<AlbumDetails> getAll() {
        String consultaSQL = "SELECT * FROM " + tablaAlbum.TABLE_NAME;
        ArrayList<AlbumDetails> listAlbum = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                AlbumDetails album = new AlbumDetails(
                        cursor.getInt(cursor.getColumnIndex(tablaAlbum.COL_ID)),
                        cursor.getString(cursor.getColumnIndex(tablaAlbum.COL_NOMBRE)),
                        cursor.getString(cursor.getColumnIndex(tablaAlbum.COL_ARTISTA)),
                        cursor.getString(cursor.getColumnIndex(tablaAlbum.COL_IMAGEN)),
                        cursor.getString(cursor.getColumnIndex(tablaAlbum.COL_TRACKS))
                );

                listAlbum.add(album);
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return listAlbum;
    }
}
