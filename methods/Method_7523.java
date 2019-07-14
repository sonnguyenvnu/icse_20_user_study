public long readInt64(boolean exception){
  try {
    return buffer.getLong();
  }
 catch (  Exception e) {
    if (exception) {
      throw new RuntimeException("read int64 error",e);
    }
 else {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.e("read int64 error");
      }
    }
  }
  return 0;
}
