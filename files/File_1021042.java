package com.zlm.hp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zlm.hp.libs.utils.DateUtil;
import com.zlm.hp.model.AudioInfo;
import com.zlm.hp.model.DownloadInfo;
import com.zlm.hp.utils.PingYinUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * éŸ³é¢‘æ•°æ?®åº“å¤„ç?†
 * Created by zhangliangming on 2017/8/5.
 */
public class AudioInfoDB extends SQLiteOpenHelper {

    private Context mContext;

    /**
     * è¡¨å??
     */
    public static final String TBL_NAME = "audioInfoTbl";

    /**
     * å»ºè¡¨è¯­å?¥
     */
    public static final String CREATE_TBL = "create table " + TBL_NAME + "("
            + "songName text," + "singerName text," + "hash text,"
            + "fileExt text," + "fileSize long," + "fileSizeText text,"
            + "filePath text," + "duration long," + "durationText text," + "downloadUrl text,"
            + "createTime text," + "status long,"
            + "type long," + "category text," + "childCategory text"
            + ")";


    private static AudioInfoDB _AudioInfoDB;

    public AudioInfoDB(Context context) {
        super(context, "hp_audioinfo.db", null, 2);
        this.mContext = context;
    }

    public static AudioInfoDB getAudioInfoDB(Context context) {
        if (_AudioInfoDB == null) {
            _AudioInfoDB = new AudioInfoDB(context);
        }
        return _AudioInfoDB;
    }

    /**
     * èŽ·å?–ContentValues
     *
     * @param audioInfo
     */
    private ContentValues getContentValues(AudioInfo audioInfo) {

        ContentValues values = new ContentValues();
        //
        values.put("songName", audioInfo.getSongName());
        values.put("singerName", audioInfo.getSingerName());
        values.put("hash", audioInfo.getHash());
        values.put("fileExt", audioInfo.getFileExt());
        values.put("fileSize", audioInfo.getFileSize());
        values.put("fileSizeText", audioInfo.getFileSizeText());
        values.put("filePath", audioInfo.getFilePath());
        values.put("duration", audioInfo.getDuration());
        values.put("durationText", audioInfo.getDurationText());
        values.put("downloadUrl", audioInfo.getDownloadUrl());
        values.put("createTime", audioInfo.getCreateTime());
        values.put("status", audioInfo.getStatus());
        values.put("type", audioInfo.getType());


        //èŽ·å?–ç´¢å¼•
        String category = PingYinUtil.getPingYin(audioInfo.getSingerName())
                .toUpperCase();
        char cat = category.charAt(0);
        if (cat <= 'Z' && cat >= 'A') {
            audioInfo.setCategory(cat + "");
            audioInfo.setChildCategory(category);
        } else {
            audioInfo.setCategory("^");
            audioInfo.setChildCategory(category);
        }

        values.put("category", audioInfo.getCategory());
        values.put("childCategory", audioInfo.getChildCategory());

        return values;
    }

    /**
     * æ·»åŠ 
     *
     * @param audioInfo
     */
    public boolean add(AudioInfo audioInfo) {

        List<ContentValues> values = new ArrayList<ContentValues>();
        ContentValues value = getContentValues(audioInfo);
        values.add(value);

        return insert(values);
    }

    /**
     * æ·»åŠ s
     *
     * @param audioInfos
     */
    public boolean add(List<AudioInfo> audioInfos) {
        List<ContentValues> values = new ArrayList<ContentValues>();
        for (AudioInfo audioInfo : audioInfos) {
            values.add(getContentValues(audioInfo));
        }
        return insert(values);
    }

    /**
     * æ?’å…¥æ•°æ?®
     *
     * @param values
     * @return
     */
    private boolean insert(List<ContentValues> values) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction(); // æ‰‹åŠ¨è®¾ç½®å¼€å§‹äº‹åŠ¡

            for (ContentValues value : values) {

                db.insert(TBL_NAME, null, value);
            }
            db.setTransactionSuccessful(); // è®¾ç½®äº‹åŠ¡å¤„ç?†æˆ?åŠŸï¼Œä¸?è®¾ç½®ä¼šè‡ªåŠ¨å›žæ»šä¸?æ??äº¤
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction(); // å¤„ç?†å®Œæˆ?
        }
        return false;
    }

    /**
     * åˆ é™¤hashå¯¹åº”çš„æ•°æ?®
     */
    public void delete(String hash) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.delete(TBL_NAME, "hash=?", new String[]{hash});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * æ˜¯å?¦å­˜åœ¨
     *
     * @param hash
     * @return
     */
    public boolean isExists(String hash) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TBL_NAME, new String[]{},
                " hash=?", new String[]{hash}, null, null, null);
        if (!cursor.moveToNext()) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    /**
     * åˆ é™¤è¡¨
     */
    public void deleteTab() {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL("drop table if exists " + TBL_NAME);
            db.execSQL(CREATE_TBL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * èŽ·å?–æœ¬åœ°æ­Œæ›²æ€»æ•°
     *
     * @return
     */
    public int getLocalAudioCount() {
        SQLiteDatabase db = getReadableDatabase();
        String args[] = {AudioInfo.LOCAL + "", AudioInfo.DOWNLOAD + "", AudioInfo.FINISH + ""};
        Cursor cursor = db.rawQuery("select count(*)from " + TBL_NAME
                + " WHERE type=? or ( type=? and status=? )", args);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    /**
     * èŽ·å?–æ‰€æœ‰æœ¬åœ°æ­Œæ›²åˆ†ç±»
     *
     * @return
     */
    public List<String> getAllLocalCategory() {

        // ç¬¬ä¸€ä¸ªå?‚æ•°Stringï¼šè¡¨å??
        // ç¬¬äºŒä¸ªå?‚æ•°String[]:è¦?æŸ¥è¯¢çš„åˆ—å??
        // ç¬¬ä¸‰ä¸ªå?‚æ•°Stringï¼šæŸ¥è¯¢æ?¡ä»¶
        // ç¬¬å››ä¸ªå?‚æ•°String[]ï¼šæŸ¥è¯¢æ?¡ä»¶çš„å?‚æ•°
        // ç¬¬äº”ä¸ªå?‚æ•°String:å¯¹æŸ¥è¯¢çš„ç»“æžœè¿›è¡Œåˆ†ç»„
        // ç¬¬å…­ä¸ªå?‚æ•°Stringï¼šå¯¹åˆ†ç»„çš„ç»“æžœè¿›è¡Œé™?åˆ¶
        // ç¬¬ä¸ƒä¸ªå?‚æ•°Stringï¼šå¯¹æŸ¥è¯¢çš„ç»“æžœè¿›è¡ŒæŽ’åº?
        List<String> list = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        String args[] = {AudioInfo.LOCAL + "", AudioInfo.DOWNLOAD + "", AudioInfo.FINISH + ""};
        Cursor cursor = db.query(true, TBL_NAME, new String[]{"category"},
                "type=? or ( type=? and status=? )", args,
                null, null, "category asc , childCategory asc", null);
        while (cursor.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndex("category")));
        }
        cursor.close();
        String baseCategory = "^";
        if (!list.contains(baseCategory)) {
            list.add(baseCategory);
        }
        return list;
    }

    /**
     * èŽ·å?–åˆ†ç±»ä¸‹çš„æ­Œæ›²
     *
     * @param category
     * @return
     */
    public List<Object> getLocalAudio(String category) {
        List<Object> list = new ArrayList<Object>();
        SQLiteDatabase db = getReadableDatabase();
        String args[] = {category, AudioInfo.LOCAL + "", AudioInfo.DOWNLOAD + "", AudioInfo.FINISH + ""};
        Cursor cursor = db.query(TBL_NAME, null,
                "category= ? and (type=? or ( type=? and status=? ))", args, null, null,
                "childCategory asc", null);
        while (cursor.moveToNext()) {
            AudioInfo audioInfo = getAudioInfoFrom(cursor);
            list.add(audioInfo);
        }
        cursor.close();
        return list;
    }

    /**
     * èŽ·å?–æ‰€æœ‰æœ¬åœ°æ­Œæ›²
     *
     * @return
     */
    public List<AudioInfo> getAllLocalAudio() {
        List<AudioInfo> list = new ArrayList<AudioInfo>();
        SQLiteDatabase db = getReadableDatabase();
        String args[] = {AudioInfo.LOCAL + "", AudioInfo.DOWNLOAD + "", AudioInfo.FINISH + ""};
        Cursor cursor = db.query(TBL_NAME, null,
                "type=? or ( type=? and status=? )", args, null, null,
                "category asc ,childCategory asc", null);
        while (cursor.moveToNext()) {
            AudioInfo audioInfo = getAudioInfoFrom(cursor);
            File audioFile = new File(audioInfo.getFilePath());
            if (!audioFile.exists()) {
                continue;
            }
            list.add(audioInfo);
        }
        cursor.close();
        return list;
    }

    /**
     * é€šè¿‡hashèŽ·å?–æ­Œæ›²
     *
     * @param hash
     * @return
     */
    public AudioInfo getAudioInfoByHash(String hash) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TBL_NAME
                + " where hash=?", new String[]{hash + ""});
        if (!cursor.moveToNext()) {
            return null;
        }
        AudioInfo audioInfo = getAudioInfoFrom(cursor);
        cursor.close();
        return audioInfo;
    }

    /**
     * èŽ·å?–æ•°æ?®
     *
     * @param cursor
     * @return
     */
    public AudioInfo getAudioInfoFrom(Cursor cursor) {
        AudioInfo audioInfo = new AudioInfo();

        audioInfo.setSongName(cursor.getString(cursor.getColumnIndex("songName")));
        audioInfo.setSingerName(cursor.getString(cursor.getColumnIndex("singerName")));
        audioInfo.setHash(cursor.getString(cursor.getColumnIndex("hash")));
        audioInfo.setFilePath(cursor.getString(cursor.getColumnIndex("filePath")));
        audioInfo.setFileExt(cursor.getString(cursor.getColumnIndex("fileExt")));
        audioInfo.setFileSize(cursor.getLong(cursor.getColumnIndex("fileSize")));
        audioInfo.setFileSizeText(cursor.getString(cursor.getColumnIndex("fileSizeText")));
        audioInfo.setDuration(cursor.getLong(cursor.getColumnIndex("duration")));
        audioInfo.setDurationText(cursor.getString(cursor.getColumnIndex("durationText")));
        audioInfo.setDownloadUrl(cursor.getString(cursor.getColumnIndex("downloadUrl")));
        audioInfo.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
        audioInfo.setStatus(cursor.getInt(cursor
                .getColumnIndex("status")));
        audioInfo.setType(cursor.getInt(cursor
                .getColumnIndex("type")));
        audioInfo.setCategory(cursor.getString(cursor.getColumnIndex("category")));
        audioInfo.setChildCategory(cursor.getString(cursor.getColumnIndex("childCategory")));

        return audioInfo;
    }
    //ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ä¸‹è½½ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?


    /**
     * åˆ¤æ–­ç½‘ç»œæ­Œæ›²æ˜¯å?¦åœ¨æœ¬åœ°
     *
     * @param hash
     * @return
     */
    public boolean isNetAudioExists(String hash) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TBL_NAME, new String[]{},
                " hash=? and status=?", new String[]{hash, AudioInfo.FINISH + ""}, null, null, null);
        if (!cursor.moveToNext()) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    /**
     * èŽ·å?–æ­£åœ¨ä¸‹è½½ä»»åŠ¡
     *
     * @return
     */
    public List<Object> getDownloadingAudio() {
        List<Object> list = new ArrayList<Object>();
        SQLiteDatabase db = getReadableDatabase();
        String args[] = {AudioInfo.DOWNLOAD + "", AudioInfo.INIT + "", AudioInfo.DOWNLOADING + ""};
        Cursor cursor = db.query(TBL_NAME, null,
                "type=? and (status=? or status=?)", args, null, null,
                "createTime desc", null);
        while (cursor.moveToNext()) {
            AudioInfo audioInfo = getAudioInfoFrom(cursor);
            File audioFile = new File(audioInfo.getFilePath());
            if (!audioFile.exists() && audioInfo.getStatus() == AudioInfo.FINISH) {
                continue;
            }
            //
            DownloadInfo downloadInfo = new DownloadInfo();
            downloadInfo.setAudioInfo(audioInfo);
            //èŽ·å?–ä¸‹è½½è¿›åº¦
            DownloadInfoDB.getAudioInfoDB(mContext).getDownloadInfoByHash(downloadInfo, audioInfo.getHash());

            list.add(downloadInfo);
        }
        cursor.close();
        return list;
    }

    /**
     * èŽ·å?–å·²ä¸‹è½½
     *
     * @return
     */
    public List<Object> getDownloadedAudio() {
        List<Object> list = new ArrayList<Object>();
        SQLiteDatabase db = getReadableDatabase();
        String args[] = {AudioInfo.DOWNLOAD + "", AudioInfo.FINISH + ""};
        Cursor cursor = db.query(TBL_NAME, null,
                "type=? and status=?", args, null, null,
                "createTime desc", null);
        while (cursor.moveToNext()) {
            AudioInfo audioInfo = getAudioInfoFrom(cursor);
            File audioFile = new File(audioInfo.getFilePath());
            if (!audioFile.exists() && audioInfo.getStatus() == AudioInfo.FINISH) {
                continue;
            }
            //
            DownloadInfo downloadInfo = new DownloadInfo();
            downloadInfo.setAudioInfo(audioInfo);
            //èŽ·å?–ä¸‹è½½è¿›åº¦
            DownloadInfoDB.getAudioInfoDB(mContext).getDownloadInfoByHash(downloadInfo, audioInfo.getHash());

            list.add(downloadInfo);
        }
        cursor.close();
        return list;
    }

    /**
     * æ›´æ–°
     *
     * @param hash
     */
    public void updateDonwloadInfo(String hash, int status) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", status);

        try {
            db.update(TBL_NAME, values,
                    "type=? and hash=? ",
                    new String[]{AudioInfo.DOWNLOAD + "", hash});
        } catch (SQLException e) {
            Log.i("error", "update failed");
        }
    }

    /**
     * èŽ·å?–ä¸‹è½½æ­Œæ›²ä¸ªæ•°
     *
     * @return
     */
    public int getDonwloadAudioCount() {
        SQLiteDatabase db = getReadableDatabase();
        String args[] = {AudioInfo.DOWNLOAD + ""};
        Cursor cursor = db.rawQuery("select count(*)from " + TBL_NAME
                + " WHERE type=? ", args);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }


    /**
     * åˆ é™¤hashå¯¹åº”çš„æ•°æ?®
     */
    public void deleteDonwloadAudio(String hash) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.delete(TBL_NAME, "hash=? and type=?", new String[]{hash, AudioInfo.DOWNLOAD + ""});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * æ·»åŠ 
     *
     * @param audioInfo
     */
    public boolean addDonwloadAudio(AudioInfo audioInfo) {

        List<ContentValues> values = new ArrayList<ContentValues>();
        ContentValues value = getContentValues(audioInfo);

        value.put("type", AudioInfo.DOWNLOAD);
        value.put("status", AudioInfo.INIT);

        values.add(value);

        return insert(values);
    }

    /////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\///æœ€è¿‘ã€?å–œæ¬¢///////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\/////////////////////

    /**
     * æ·»åŠ 
     *
     * @param audioInfo
     */

    public boolean addRecentOrLikeAudio(AudioInfo audioInfo, boolean isRecent) {
        int type = audioInfo.getType();
        if (type == AudioInfo.NET) {
            if (isRecent)
                type = AudioInfo.RECENT_NET;
            else type = AudioInfo.LIKE_NET;
        } else {
            if (isRecent)
                type = AudioInfo.RECENT_LOCAL;
            else type = AudioInfo.LIKE_LOCAL;
        }

        //æ›´æ–°åˆ›å»ºæ—¶é—´
        audioInfo.setCreateTime(DateUtil.parseDateToString(new Date()));
        List<ContentValues> values = new ArrayList<ContentValues>();
        ContentValues value = getContentValues(audioInfo);
        value.put("type", type);

        values.add(value);

        return insert(values);
    }

    /**
     * æ˜¯å?¦å­˜åœ¨
     *
     * @param hash
     * @return
     */
    public boolean isRecentOrLikeExists(String hash, int type, boolean isRecent) {

        String typeString = "";
        if (type == AudioInfo.NET) {
            if (isRecent)
                typeString = AudioInfo.RECENT_NET + "";
            else typeString = AudioInfo.LIKE_NET + "";
        } else {
            if (isRecent)
                typeString = AudioInfo.RECENT_LOCAL + "";
            else typeString = AudioInfo.LIKE_LOCAL + "";
        }

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TBL_NAME, new String[]{},
                " hash=? and type=?", new String[]{hash, typeString}, null, null, null);
        if (!cursor.moveToNext()) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    /**
     * åˆ é™¤hashå¯¹åº”çš„æ•°æ?®
     */
    public void deleteRecentOrLikeAudio(String hash, int type, boolean isRecent) {
        String typeString = "";
        if (type == AudioInfo.NET) {
            if (isRecent)
                typeString = AudioInfo.RECENT_NET + "";
            else typeString = AudioInfo.LIKE_NET + "";
        } else {
            if (isRecent)
                typeString = AudioInfo.RECENT_LOCAL + "";
            else typeString = AudioInfo.LIKE_LOCAL + "";
        }
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.delete(TBL_NAME, "hash=? and type=?", new String[]{hash, typeString});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * æ›´æ–°æœ€è¿‘æ­Œæ›²æ•°æ?®
     *
     * @param hash
     * @return
     */
    public boolean updateRecentAudio(String hash, int type, boolean isRecent) {
        String typeString = "";
        if (type == AudioInfo.NET) {
            if (isRecent)
                typeString = AudioInfo.RECENT_NET + "";
            else typeString = AudioInfo.LIKE_NET + "";
        } else {
            if (isRecent)
                typeString = AudioInfo.RECENT_LOCAL + "";
            else typeString = AudioInfo.LIKE_LOCAL + "";
        }
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("createTime", DateUtil.parseDateToString(new Date()));

        try {
            db.update(TBL_NAME, values, "hash=? and type=?",
                    new String[]{hash, typeString});
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * èŽ·å?–æœ€è¿‘æ­Œæ›²æ€»æ•°
     *
     * @return
     */
    public int getRecentAudioCount() {
        SQLiteDatabase db = getReadableDatabase();
        String args[] = {AudioInfo.RECENT_LOCAL + "", AudioInfo.RECENT_NET + ""};
        Cursor cursor = db.rawQuery("select count(*)from " + TBL_NAME
                + " WHERE type=? or type=? ", args);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    /**
     * èŽ·å?–å–œæ¬¢æ­Œæ›²æ€»æ•°
     *
     * @return
     */
    public int getLikeAudioCount() {
        SQLiteDatabase db = getReadableDatabase();
        String args[] = {AudioInfo.LIKE_LOCAL + "", AudioInfo.LIKE_NET + ""};
        Cursor cursor = db.rawQuery("select count(*)from " + TBL_NAME
                + " WHERE type=? or type=? ", args);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    /**
     * èŽ·å?–æœ€è¿‘æ‰€æœ‰æ­Œæ›²
     *
     * @return
     */
    public List<AudioInfo> getAllRecentAudio() {
        List<AudioInfo> list = new ArrayList<AudioInfo>();
        SQLiteDatabase db = getReadableDatabase();
        String args[] = {AudioInfo.RECENT_LOCAL + "", AudioInfo.RECENT_NET + ""};
        Cursor cursor = db.query(TBL_NAME, null,
                "type=? or type=?", args, null, null,
                "createTime desc", null);
        while (cursor.moveToNext()) {
            AudioInfo audioInfo = getAudioInfoFrom(cursor);
            //
            if (audioInfo.getType() == AudioInfo.RECENT_LOCAL) {
                audioInfo.setType(AudioInfo.LOCAL);
            } else {
                audioInfo.setType(AudioInfo.NET);
            }
            list.add(audioInfo);
        }
        cursor.close();
        return list;
    }

    /**
     * èŽ·å?–æ‰€æœ‰å–œæ¬¢çš„æ­Œæ›²åˆ—è¡¨
     *
     * @return
     */
    public List<AudioInfo> getAllLikeAudio() {
        List<AudioInfo> list = new ArrayList<AudioInfo>();
        SQLiteDatabase db = getReadableDatabase();
        String args[] = {AudioInfo.LIKE_LOCAL + "", AudioInfo.LIKE_NET + ""};
        Cursor cursor = db.query(TBL_NAME, null,
                "type=? or type=?", args, null, null,
                "createTime desc", null);
        while (cursor.moveToNext()) {
            AudioInfo audioInfo = getAudioInfoFrom(cursor);
            //
            if (audioInfo.getType() == AudioInfo.LIKE_LOCAL) {
                audioInfo.setType(AudioInfo.LOCAL);
            } else {
                audioInfo.setType(AudioInfo.NET);
            }
            list.add(audioInfo);
        }
        cursor.close();
        return list;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TBL);
        } catch (SQLException e) {
            Log.i("error", "create table failed");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        try {
            db.execSQL("drop table if exists " + TBL_NAME);
        } catch (SQLException e) {
            Log.i("error", "drop table failed");
        }
        onCreate(db);
    }
}
