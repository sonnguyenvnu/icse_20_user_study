private long[] updateMessageStateAndIdInternal(long random_id,Integer _oldId,int newId,int date,int channelId){
  SQLiteCursor cursor=null;
  long oldMessageId;
  long newMessageId=newId;
  if (_oldId == null) {
    try {
      cursor=database.queryFinalized(String.format(Locale.US,"SELECT mid FROM randoms WHERE random_id = %d LIMIT 1",random_id));
      if (cursor.next()) {
        _oldId=cursor.intValue(0);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
 finally {
      if (cursor != null) {
        cursor.dispose();
      }
    }
    if (_oldId == null) {
      return null;
    }
  }
  oldMessageId=_oldId;
  if (channelId != 0) {
    oldMessageId|=((long)channelId) << 32;
    newMessageId|=((long)channelId) << 32;
  }
  long did=0;
  try {
    cursor=database.queryFinalized(String.format(Locale.US,"SELECT uid FROM messages WHERE mid = %d LIMIT 1",oldMessageId));
    if (cursor.next()) {
      did=cursor.longValue(0);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
 finally {
    if (cursor != null) {
      cursor.dispose();
    }
  }
  if (did == 0) {
    return null;
  }
  if (oldMessageId == newMessageId && date != 0) {
    SQLitePreparedStatement state=null;
    try {
      state=database.executeFast("UPDATE messages SET send_state = 0, date = ? WHERE mid = ?");
      state.bindInteger(1,date);
      state.bindLong(2,newMessageId);
      state.step();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
 finally {
      if (state != null) {
        state.dispose();
      }
    }
    return new long[]{did,newId};
  }
 else {
    SQLitePreparedStatement state=null;
    try {
      state=database.executeFast("UPDATE messages SET mid = ?, send_state = 0 WHERE mid = ?");
      state.bindLong(1,newMessageId);
      state.bindLong(2,oldMessageId);
      state.step();
    }
 catch (    Exception e) {
      try {
        database.executeFast(String.format(Locale.US,"DELETE FROM messages WHERE mid = %d",oldMessageId)).stepThis().dispose();
        database.executeFast(String.format(Locale.US,"DELETE FROM messages_seq WHERE mid = %d",oldMessageId)).stepThis().dispose();
      }
 catch (      Exception e2) {
        FileLog.e(e2);
      }
    }
 finally {
      if (state != null) {
        state.dispose();
        state=null;
      }
    }
    try {
      state=database.executeFast("UPDATE media_v2 SET mid = ? WHERE mid = ?");
      state.bindLong(1,newMessageId);
      state.bindLong(2,oldMessageId);
      state.step();
    }
 catch (    Exception e) {
      try {
        database.executeFast(String.format(Locale.US,"DELETE FROM media_v2 WHERE mid = %d",oldMessageId)).stepThis().dispose();
      }
 catch (      Exception e2) {
        FileLog.e(e2);
      }
    }
 finally {
      if (state != null) {
        state.dispose();
        state=null;
      }
    }
    try {
      state=database.executeFast("UPDATE dialogs SET last_mid = ? WHERE last_mid = ?");
      state.bindLong(1,newMessageId);
      state.bindLong(2,oldMessageId);
      state.step();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
 finally {
      if (state != null) {
        state.dispose();
      }
    }
    return new long[]{did,_oldId};
  }
}
