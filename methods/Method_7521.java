public int readInt32(boolean exception){
  try {
    return buffer.getInt();
  }
 catch (  Exception e) {
    if (exception) {
      throw new RuntimeException("read int32 error",e);
    }
 else {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.e("read int32 error");
      }
    }
  }
  return 0;
}
