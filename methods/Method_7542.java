public long readInt64(boolean exception){
  try {
    long i=0;
    for (int j=0; j < 8; j++) {
      i|=((long)in.read() << (j * 8));
      len++;
    }
    return i;
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
