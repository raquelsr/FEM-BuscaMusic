package fem.miw.upm.es.buscamusic.modelsTopTracks;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import static fem.miw.upm.es.buscamusic.modelsTopTracks.TrackContract.tablaTopTracks;

public class TopTracksProvider extends ContentProvider {

    private static final String AUTHORITY = TopTracksProvider.class.getPackage().getName() + ".provider";
    private static final String ENTRY = "topTracks";

    private static final String uri = "content://" + AUTHORITY + "/" + ENTRY;
    public static final Uri CONTENT_URI = Uri.parse(uri);

    RepositorioTopTracks db_topTracks;

    private static final int ID_URI_TOP_TRACKS = 1;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, ENTRY + "/*", ID_URI_TOP_TRACKS);
    }

    @Override
    public boolean onCreate() {
        db_topTracks = new RepositorioTopTracks(getContext());
        return true;
    }

    @Override
    public Cursor query(final Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Log.i("MiW", "HA ENTRADO EN LA QUERY");
        Log.i ("MiW", uri.getLastPathSegment());
        SQLiteDatabase db_query = db_topTracks.getReadableDatabase();
        String where = tablaTopTracks.COL_ID + " <= ?";
        selectionArgs = new String[1];
        selectionArgs[0] = uri.getLastPathSegment();

        Cursor c = db_query.query(tablaTopTracks.TABLE_NAME,
                projection,
                where,
                selectionArgs,null,null,
                sortOrder);

        if (c.getCount() == 0){
            Log.i("MiW", "Probando");
            Log.i("MiW", "Por aqui");

            Thread buscar = new Thread(new Runnable() {
                @Override
                public void run() {
                    TopTracksApi buscarTracks = new TopTracksApi(getContext(),null);
                    buscarTracks.infoTopTrackAPI();
                }
            });
            buscar.start();

            c = db_query.query(tablaTopTracks.TABLE_NAME,
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
            case ID_URI_TOP_TRACKS:
                return "vnd.android.cursor.dir/vnd.miw.toptracks";
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
