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
    private static final String ENTRY = "artist";

    private static final String uri = "content://" + AUTHORITY + "/" + ENTRY;
    public static final Uri CONTENT_URI = Uri.parse(uri);

    RepositorioArtist db_artist;

    private static final int ARTIST = 1;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("fem.miw.upm.es.buscamusic.modelsArtist.provider", "artist", ARTIST);
    }

    @Override
    public boolean onCreate() {
        db_artist = new RepositorioArtist(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db_query = db_artist.getReadableDatabase();
        Cursor c = db_query.query(tablaArtista.TABLE_NAME,
                projection,
                selection,
                selectionArgs,null,null,
                sortOrder);

        return c;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case ARTIST:
                return "vnd.android.cursor.item/vnd.miw.artist";
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
        return 0;
    }
}
