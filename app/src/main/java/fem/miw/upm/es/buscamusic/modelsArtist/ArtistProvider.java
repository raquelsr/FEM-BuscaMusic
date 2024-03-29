package fem.miw.upm.es.buscamusic.modelsArtist;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import static fem.miw.upm.es.buscamusic.modelsArtist.ArtistContract.tablaArtista;

public class ArtistProvider extends ContentProvider {

    private static final String AUTHORITY = ArtistProvider.class.getPackage().getName() + ".provider";
    private static final String ENTRY = "artistas";

    private static final String uri = "content://" + AUTHORITY + "/" + ENTRY;

    RepositorioArtist db_artist;

    private static final int ID_URI_ARTISTA_NOMBRE = 1;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, ENTRY + "/*", ID_URI_ARTISTA_NOMBRE);
    }

    @Override
    public boolean onCreate() {
        db_artist = new RepositorioArtist(getContext());
        return true;
    }

    @Override
    public Cursor query(final Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db_query = db_artist.getReadableDatabase();
        String where = tablaArtista.COL_NOMBRE + " LIKE ?";
        selectionArgs = new String[1];
        selectionArgs[0] = uri.getLastPathSegment();

        Cursor c = db_query.query(tablaArtista.TABLE_NAME,
                projection,
                where,
                selectionArgs,null,null,
                sortOrder);

        if (c.getCount() == 0){

            Thread buscar = new Thread(new Runnable() {
                @Override
                public void run() {
                    ArtistApi busquedaArtista = new ArtistApi(getContext(),null,null);
                    busquedaArtista.infoArtistAPI(uri.getLastPathSegment());
                }
            });
            buscar.start();

            c = db_query.query(tablaArtista.TABLE_NAME,
                    projection,
                    where,
                    selectionArgs,null,null,
                    sortOrder);
        }

        return c;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case ID_URI_ARTISTA_NOMBRE:
                return "vnd.android.cursor.item/vnd.miw.artista";
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        String where;
        where = "nombre = \"" + uri.getLastPathSegment() + "\"" ;

        SQLiteDatabase db = db_artist.getWritableDatabase();
        int filasActualizadas = db.update(tablaArtista.TABLE_NAME, values, where, selectionArgs);

        return filasActualizadas;
    }
}
