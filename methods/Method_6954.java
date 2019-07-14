private void updateDbToLastVersion(final int currentVersion){
  storageQueue.postRunnable(() -> {
    try {
      int version=currentVersion;
      if (version < 4) {
        database.executeFast("CREATE TABLE IF NOT EXISTS user_photos(uid INTEGER, id INTEGER, data BLOB, PRIMARY KEY (uid, id))").stepThis().dispose();
        database.executeFast("DROP INDEX IF EXISTS read_state_out_idx_messages;").stepThis().dispose();
        database.executeFast("DROP INDEX IF EXISTS ttl_idx_messages;").stepThis().dispose();
        database.executeFast("DROP INDEX IF EXISTS date_idx_messages;").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS mid_out_idx_messages ON messages(mid, out);").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS task_idx_messages ON messages(uid, out, read_state, ttl, date, send_state);").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS uid_date_mid_idx_messages ON messages(uid, date, mid);").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS user_contacts_v6(uid INTEGER PRIMARY KEY, fname TEXT, sname TEXT)").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS user_phones_v6(uid INTEGER, phone TEXT, sphone TEXT, deleted INTEGER, PRIMARY KEY (uid, phone))").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS sphone_deleted_idx_user_phones ON user_phones_v6(sphone, deleted);").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS mid_idx_randoms ON randoms(mid);").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS sent_files_v2(uid TEXT, type INTEGER, data BLOB, PRIMARY KEY (uid, type))").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS blocked_users(uid INTEGER PRIMARY KEY)").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS download_queue(uid INTEGER, type INTEGER, date INTEGER, data BLOB, PRIMARY KEY (uid, type));").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS type_date_idx_download_queue ON download_queue(type, date);").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS dialog_settings(did INTEGER PRIMARY KEY, flags INTEGER);").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS send_state_idx_messages ON messages(mid, send_state, date) WHERE mid < 0 AND send_state = 1;").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS unread_count_idx_dialogs ON dialogs(unread_count);").stepThis().dispose();
        database.executeFast("UPDATE messages SET send_state = 2 WHERE mid < 0 AND send_state = 1").stepThis().dispose();
        fixNotificationSettings();
        database.executeFast("PRAGMA user_version = 4").stepThis().dispose();
        version=4;
      }
      if (version == 4) {
        database.executeFast("CREATE TABLE IF NOT EXISTS enc_tasks_v2(mid INTEGER PRIMARY KEY, date INTEGER)").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS date_idx_enc_tasks_v2 ON enc_tasks_v2(date);").stepThis().dispose();
        database.beginTransaction();
        SQLiteCursor cursor=database.queryFinalized("SELECT date, data FROM enc_tasks WHERE 1");
        SQLitePreparedStatement state=database.executeFast("REPLACE INTO enc_tasks_v2 VALUES(?, ?)");
        if (cursor.next()) {
          int date=cursor.intValue(0);
          NativeByteBuffer data=cursor.byteBufferValue(1);
          if (data != null) {
            int length=data.limit();
            for (int a=0; a < length / 4; a++) {
              state.requery();
              state.bindInteger(1,data.readInt32(false));
              state.bindInteger(2,date);
              state.step();
            }
            data.reuse();
          }
        }
        state.dispose();
        cursor.dispose();
        database.commitTransaction();
        database.executeFast("DROP INDEX IF EXISTS date_idx_enc_tasks;").stepThis().dispose();
        database.executeFast("DROP TABLE IF EXISTS enc_tasks;").stepThis().dispose();
        database.executeFast("ALTER TABLE messages ADD COLUMN media INTEGER default 0").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 6").stepThis().dispose();
        version=6;
      }
      if (version == 6) {
        database.executeFast("CREATE TABLE IF NOT EXISTS messages_seq(mid INTEGER PRIMARY KEY, seq_in INTEGER, seq_out INTEGER);").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS seq_idx_messages_seq ON messages_seq(seq_in, seq_out);").stepThis().dispose();
        database.executeFast("ALTER TABLE enc_chats ADD COLUMN layer INTEGER default 0").stepThis().dispose();
        database.executeFast("ALTER TABLE enc_chats ADD COLUMN seq_in INTEGER default 0").stepThis().dispose();
        database.executeFast("ALTER TABLE enc_chats ADD COLUMN seq_out INTEGER default 0").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 7").stepThis().dispose();
        version=7;
      }
      if (version == 7 || version == 8 || version == 9) {
        database.executeFast("ALTER TABLE enc_chats ADD COLUMN use_count INTEGER default 0").stepThis().dispose();
        database.executeFast("ALTER TABLE enc_chats ADD COLUMN exchange_id INTEGER default 0").stepThis().dispose();
        database.executeFast("ALTER TABLE enc_chats ADD COLUMN key_date INTEGER default 0").stepThis().dispose();
        database.executeFast("ALTER TABLE enc_chats ADD COLUMN fprint INTEGER default 0").stepThis().dispose();
        database.executeFast("ALTER TABLE enc_chats ADD COLUMN fauthkey BLOB default NULL").stepThis().dispose();
        database.executeFast("ALTER TABLE enc_chats ADD COLUMN khash BLOB default NULL").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 10").stepThis().dispose();
        version=10;
      }
      if (version == 10) {
        database.executeFast("CREATE TABLE IF NOT EXISTS web_recent_v3(id TEXT, type INTEGER, image_url TEXT, thumb_url TEXT, local_url TEXT, width INTEGER, height INTEGER, size INTEGER, date INTEGER, PRIMARY KEY (id, type));").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 11").stepThis().dispose();
        version=11;
      }
      if (version == 11 || version == 12) {
        database.executeFast("DROP INDEX IF EXISTS uid_mid_idx_media;").stepThis().dispose();
        database.executeFast("DROP INDEX IF EXISTS mid_idx_media;").stepThis().dispose();
        database.executeFast("DROP INDEX IF EXISTS uid_date_mid_idx_media;").stepThis().dispose();
        database.executeFast("DROP TABLE IF EXISTS media;").stepThis().dispose();
        database.executeFast("DROP TABLE IF EXISTS media_counts;").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS media_v2(mid INTEGER PRIMARY KEY, uid INTEGER, date INTEGER, type INTEGER, data BLOB)").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS media_counts_v2(uid INTEGER, type INTEGER, count INTEGER, PRIMARY KEY(uid, type))").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS uid_mid_type_date_idx_media ON media_v2(uid, mid, type, date);").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS keyvalue(id TEXT PRIMARY KEY, value TEXT)").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 13").stepThis().dispose();
        version=13;
      }
      if (version == 13) {
        database.executeFast("ALTER TABLE messages ADD COLUMN replydata BLOB default NULL").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 14").stepThis().dispose();
        version=14;
      }
      if (version == 14) {
        database.executeFast("CREATE TABLE IF NOT EXISTS hashtag_recent_v2(id TEXT PRIMARY KEY, date INTEGER);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 15").stepThis().dispose();
        version=15;
      }
      if (version == 15) {
        database.executeFast("CREATE TABLE IF NOT EXISTS webpage_pending(id INTEGER, mid INTEGER, PRIMARY KEY (id, mid));").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 16").stepThis().dispose();
        version=16;
      }
      if (version == 16) {
        database.executeFast("ALTER TABLE dialogs ADD COLUMN inbox_max INTEGER default 0").stepThis().dispose();
        database.executeFast("ALTER TABLE dialogs ADD COLUMN outbox_max INTEGER default 0").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 17").stepThis().dispose();
        version=17;
      }
      if (version == 17) {
        database.executeFast("CREATE TABLE bot_info(uid INTEGER PRIMARY KEY, info BLOB)").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 18").stepThis().dispose();
        version=18;
      }
      if (version == 18) {
        database.executeFast("DROP TABLE IF EXISTS stickers;").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS stickers_v2(id INTEGER PRIMARY KEY, data BLOB, date INTEGER, hash TEXT);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 19").stepThis().dispose();
        version=19;
      }
      if (version == 19) {
        database.executeFast("CREATE TABLE IF NOT EXISTS bot_keyboard(uid INTEGER PRIMARY KEY, mid INTEGER, info BLOB)").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS bot_keyboard_idx_mid ON bot_keyboard(mid);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 20").stepThis().dispose();
        version=20;
      }
      if (version == 20) {
        database.executeFast("CREATE TABLE search_recent(did INTEGER PRIMARY KEY, date INTEGER);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 21").stepThis().dispose();
        version=21;
      }
      if (version == 21) {
        database.executeFast("CREATE TABLE IF NOT EXISTS chat_settings_v2(uid INTEGER PRIMARY KEY, info BLOB)").stepThis().dispose();
        SQLiteCursor cursor=database.queryFinalized("SELECT uid, participants FROM chat_settings WHERE uid < 0");
        SQLitePreparedStatement state=database.executeFast("REPLACE INTO chat_settings_v2 VALUES(?, ?)");
        while (cursor.next()) {
          int chat_id=cursor.intValue(0);
          NativeByteBuffer data=cursor.byteBufferValue(1);
          if (data != null) {
            TLRPC.ChatParticipants participants=TLRPC.ChatParticipants.TLdeserialize(data,data.readInt32(false),false);
            data.reuse();
            if (participants != null) {
              TLRPC.TL_chatFull chatFull=new TLRPC.TL_chatFull();
              chatFull.id=chat_id;
              chatFull.chat_photo=new TLRPC.TL_photoEmpty();
              chatFull.notify_settings=new TLRPC.TL_peerNotifySettingsEmpty_layer77();
              chatFull.exported_invite=new TLRPC.TL_chatInviteEmpty();
              chatFull.participants=participants;
              NativeByteBuffer data2=new NativeByteBuffer(chatFull.getObjectSize());
              chatFull.serializeToStream(data2);
              state.requery();
              state.bindInteger(1,chat_id);
              state.bindByteBuffer(2,data2);
              state.step();
              data2.reuse();
            }
          }
        }
        state.dispose();
        cursor.dispose();
        database.executeFast("DROP TABLE IF EXISTS chat_settings;").stepThis().dispose();
        database.executeFast("ALTER TABLE dialogs ADD COLUMN last_mid_i INTEGER default 0").stepThis().dispose();
        database.executeFast("ALTER TABLE dialogs ADD COLUMN unread_count_i INTEGER default 0").stepThis().dispose();
        database.executeFast("ALTER TABLE dialogs ADD COLUMN pts INTEGER default 0").stepThis().dispose();
        database.executeFast("ALTER TABLE dialogs ADD COLUMN date_i INTEGER default 0").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS last_mid_i_idx_dialogs ON dialogs(last_mid_i);").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS unread_count_i_idx_dialogs ON dialogs(unread_count_i);").stepThis().dispose();
        database.executeFast("ALTER TABLE messages ADD COLUMN imp INTEGER default 0").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS messages_holes(uid INTEGER, start INTEGER, end INTEGER, PRIMARY KEY(uid, start));").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS uid_end_messages_holes ON messages_holes(uid, end);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 22").stepThis().dispose();
        version=22;
      }
      if (version == 22) {
        database.executeFast("CREATE TABLE IF NOT EXISTS media_holes_v2(uid INTEGER, type INTEGER, start INTEGER, end INTEGER, PRIMARY KEY(uid, type, start));").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS uid_end_media_holes_v2 ON media_holes_v2(uid, type, end);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 23").stepThis().dispose();
        version=23;
      }
      if (version == 23 || version == 24) {
        database.executeFast("DELETE FROM media_holes_v2 WHERE uid != 0 AND type >= 0 AND start IN (0, 1)").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 25").stepThis().dispose();
        version=25;
      }
      if (version == 25 || version == 26) {
        database.executeFast("CREATE TABLE IF NOT EXISTS channel_users_v2(did INTEGER, uid INTEGER, date INTEGER, data BLOB, PRIMARY KEY(did, uid))").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 27").stepThis().dispose();
        version=27;
      }
      if (version == 27) {
        database.executeFast("ALTER TABLE web_recent_v3 ADD COLUMN document BLOB default NULL").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 28").stepThis().dispose();
        version=28;
      }
      if (version == 28 || version == 29) {
        database.executeFast("DELETE FROM sent_files_v2 WHERE 1").stepThis().dispose();
        database.executeFast("DELETE FROM download_queue WHERE 1").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 30").stepThis().dispose();
        version=30;
      }
      if (version == 30) {
        database.executeFast("ALTER TABLE chat_settings_v2 ADD COLUMN pinned INTEGER default 0").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS chat_settings_pinned_idx ON chat_settings_v2(uid, pinned) WHERE pinned != 0;").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS chat_pinned(uid INTEGER PRIMARY KEY, pinned INTEGER, data BLOB)").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS chat_pinned_mid_idx ON chat_pinned(uid, pinned) WHERE pinned != 0;").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS users_data(uid INTEGER PRIMARY KEY, about TEXT)").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 31").stepThis().dispose();
        version=31;
      }
      if (version == 31) {
        database.executeFast("DROP TABLE IF EXISTS bot_recent;").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS chat_hints(did INTEGER, type INTEGER, rating REAL, date INTEGER, PRIMARY KEY(did, type))").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS chat_hints_rating_idx ON chat_hints(rating);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 32").stepThis().dispose();
        version=32;
      }
      if (version == 32) {
        database.executeFast("DROP INDEX IF EXISTS uid_mid_idx_imp_messages;").stepThis().dispose();
        database.executeFast("DROP INDEX IF EXISTS uid_date_mid_imp_idx_messages;").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 33").stepThis().dispose();
        version=33;
      }
      if (version == 33) {
        database.executeFast("CREATE TABLE IF NOT EXISTS pending_tasks(id INTEGER PRIMARY KEY, data BLOB);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 34").stepThis().dispose();
        version=34;
      }
      if (version == 34) {
        database.executeFast("CREATE TABLE IF NOT EXISTS stickers_featured(id INTEGER PRIMARY KEY, data BLOB, unread BLOB, date INTEGER, hash TEXT);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 35").stepThis().dispose();
        version=35;
      }
      if (version == 35) {
        database.executeFast("CREATE TABLE IF NOT EXISTS requested_holes(uid INTEGER, seq_out_start INTEGER, seq_out_end INTEGER, PRIMARY KEY (uid, seq_out_start, seq_out_end));").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 36").stepThis().dispose();
        version=36;
      }
      if (version == 36) {
        database.executeFast("ALTER TABLE enc_chats ADD COLUMN in_seq_no INTEGER default 0").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 37").stepThis().dispose();
        version=37;
      }
      if (version == 37) {
        database.executeFast("CREATE TABLE IF NOT EXISTS botcache(id TEXT PRIMARY KEY, date INTEGER, data BLOB)").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS botcache_date_idx ON botcache(date);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 38").stepThis().dispose();
        version=38;
      }
      if (version == 38) {
        database.executeFast("ALTER TABLE dialogs ADD COLUMN pinned INTEGER default 0").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 39").stepThis().dispose();
        version=39;
      }
      if (version == 39) {
        database.executeFast("ALTER TABLE enc_chats ADD COLUMN admin_id INTEGER default 0").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 40").stepThis().dispose();
        version=40;
      }
      if (version == 40) {
        fixNotificationSettings();
        database.executeFast("PRAGMA user_version = 41").stepThis().dispose();
        version=41;
      }
      if (version == 41) {
        database.executeFast("ALTER TABLE messages ADD COLUMN mention INTEGER default 0").stepThis().dispose();
        database.executeFast("ALTER TABLE user_contacts_v6 ADD COLUMN imported INTEGER default 0").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS uid_mention_idx_messages ON messages(uid, mention, read_state);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 42").stepThis().dispose();
        version=42;
      }
      if (version == 42) {
        database.executeFast("CREATE TABLE IF NOT EXISTS sharing_locations(uid INTEGER PRIMARY KEY, mid INTEGER, date INTEGER, period INTEGER, message BLOB);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 43").stepThis().dispose();
        version=43;
      }
      if (version == 43) {
        database.executeFast("CREATE TABLE IF NOT EXISTS channel_admins(did INTEGER, uid INTEGER, PRIMARY KEY(did, uid))").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 44").stepThis().dispose();
        version=44;
      }
      if (version == 44) {
        database.executeFast("CREATE TABLE IF NOT EXISTS user_contacts_v7(key TEXT PRIMARY KEY, uid INTEGER, fname TEXT, sname TEXT, imported INTEGER)").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS user_phones_v7(key TEXT, phone TEXT, sphone TEXT, deleted INTEGER, PRIMARY KEY (key, phone))").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS sphone_deleted_idx_user_phones ON user_phones_v7(sphone, deleted);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 45").stepThis().dispose();
        version=45;
      }
      if (version == 45) {
        database.executeFast("ALTER TABLE enc_chats ADD COLUMN mtproto_seq INTEGER default 0").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 46").stepThis().dispose();
        version=46;
      }
      if (version == 46) {
        database.executeFast("DELETE FROM botcache WHERE 1").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 47").stepThis().dispose();
        version=47;
      }
      if (version == 47) {
        database.executeFast("ALTER TABLE dialogs ADD COLUMN flags INTEGER default 0").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 48").stepThis().dispose();
        version=48;
      }
      if (version == 48) {
        database.executeFast("CREATE TABLE IF NOT EXISTS unread_push_messages(uid INTEGER, mid INTEGER, random INTEGER, date INTEGER, data BLOB, fm TEXT, name TEXT, uname TEXT, flags INTEGER, PRIMARY KEY(uid, mid))").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS unread_push_messages_idx_date ON unread_push_messages(date);").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS unread_push_messages_idx_random ON unread_push_messages(random);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 49").stepThis().dispose();
        version=49;
      }
      if (version == 49) {
        database.executeFast("DELETE FROM chat_pinned WHERE uid = 1").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS user_settings(uid INTEGER PRIMARY KEY, info BLOB, pinned INTEGER)").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS user_settings_pinned_idx ON user_settings(uid, pinned) WHERE pinned != 0;").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 50").stepThis().dispose();
        version=50;
      }
      if (version == 50) {
        database.executeFast("DELETE FROM sent_files_v2 WHERE 1").stepThis().dispose();
        database.executeFast("ALTER TABLE sent_files_v2 ADD COLUMN parent TEXT").stepThis().dispose();
        database.executeFast("DELETE FROM download_queue WHERE 1").stepThis().dispose();
        database.executeFast("ALTER TABLE download_queue ADD COLUMN parent TEXT").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 51").stepThis().dispose();
        version=51;
      }
      if (version == 51) {
        database.executeFast("ALTER TABLE media_counts_v2 ADD COLUMN old INTEGER").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 52").stepThis().dispose();
        version=52;
      }
      if (version == 52) {
        database.executeFast("CREATE TABLE IF NOT EXISTS polls(mid INTEGER PRIMARY KEY, id INTEGER);").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS polls_id ON polls(id);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 53").stepThis().dispose();
        version=53;
      }
      if (version == 53) {
        database.executeFast("ALTER TABLE chat_settings_v2 ADD COLUMN online INTEGER default 0").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 54").stepThis().dispose();
        version=54;
      }
      if (version == 54) {
        database.executeFast("DROP TABLE IF EXISTS wallpapers;").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 55").stepThis().dispose();
        version=55;
      }
      if (version == 55) {
        database.executeFast("CREATE TABLE IF NOT EXISTS wallpapers2(uid INTEGER PRIMARY KEY, data BLOB, num INTEGER)").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS wallpapers_num ON wallpapers2(num);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 56").stepThis().dispose();
        version=56;
      }
      if (version == 56 || version == 57) {
        database.executeFast("CREATE TABLE IF NOT EXISTS emoji_keywords_v2(lang TEXT, keyword TEXT, emoji TEXT, PRIMARY KEY(lang, keyword, emoji));").stepThis().dispose();
        database.executeFast("CREATE TABLE IF NOT EXISTS emoji_keywords_info_v2(lang TEXT PRIMARY KEY, alias TEXT, version INTEGER);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 58").stepThis().dispose();
        version=58;
      }
      if (version == 58) {
        database.executeFast("CREATE INDEX IF NOT EXISTS emoji_keywords_v2_keyword ON emoji_keywords_v2(keyword);").stepThis().dispose();
        database.executeFast("ALTER TABLE emoji_keywords_info_v2 ADD COLUMN date INTEGER default 0").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 59").stepThis().dispose();
        version=59;
      }
      if (version == 59) {
        database.executeFast("ALTER TABLE dialogs ADD COLUMN folder_id INTEGER default 0").stepThis().dispose();
        database.executeFast("ALTER TABLE dialogs ADD COLUMN data BLOB default NULL").stepThis().dispose();
        database.executeFast("CREATE INDEX IF NOT EXISTS folder_id_idx_dialogs ON dialogs(folder_id);").stepThis().dispose();
        database.executeFast("PRAGMA user_version = 60").stepThis().dispose();
        version=60;
      }
      if (version == 60) {
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
