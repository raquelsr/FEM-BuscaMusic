package fem.miw.upm.es.buscamusic.modelsAlbum;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

import static fem.miw.upm.es.buscamusic.modelsAlbum.AlbumContract.tablaAlbum;

public class AlbumProvider extends ContentProvider {

    private static final String AUTHORITY = AlbumProvider.class.getPackage().getName() + ".provider";
    private static final String ENTRY = "albums";

    private static final String uri = "content://" + AUTHORITY + "/" + ENTRY;
    public static final Uri CONTENT_URI = Uri.parse(uri);

    RepositorioAlbum db_album;

    private static final int ID_URI_ALBUM_NOMBRE = 1;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, ENTRY + "/*", ID_URI_ALBUM_NOMBRE);
    }

    @Override
    public boolean onCreate() {
        db_album = new RepositorioAlbum(getContext());
        return true;
    }

    @Override
    public Cursor query(@NonNull final Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db_query = db_album.getReadableDatabase();
        String where = tablaAlbum.COL_NOMBRE + " LIKE ?";
        selectionArgs = new String[1];
        selectionArgs[0] = uri.getLastPathSegment();

        Cursor c = db_query.query(tablaAlbum.TABLE_NAME,
                projection,
                where,
                selectionArgs,null,null,
                sortOrder);

        if (c.getCount() == 0){
            Thread busqueda = new Thread(new Runnable() {
                @Override
                public void run() {
                    AlbumApi buscaralbum = new AlbumApi(getContext(),null,null);
                    buscaralbum.infoAlbumAPI(uri.getPathSegments().get(1), uri.getPathSegments().get(2));
                }
            });
            busqueda.start();

            c = db_query.query(tablaAlbum.TABLE_NAME,
                    projection,
                    where,
                    selectionArgs,null,null,
                    sortOrder);
        }

        return c;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case ID_URI_ALBUM_NOMBRE:
                return "vnd.android.cursor.item/vnd.miw.album";
            default:
                return null;
        }
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
