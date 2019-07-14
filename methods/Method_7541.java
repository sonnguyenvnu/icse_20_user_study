public int readInt32(boolean exception){
  try {
    int i=0;
    for (int j=0; j < 4; j++) {
      i|=(in.read() << (j * 8));
      len++;
    }
    return i;
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
