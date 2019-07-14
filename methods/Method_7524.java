public void readBytes(byte[] b,boolean exception){
  try {
    buffer.get(b);
  }
 catch (  Exception e) {
    if (exception) {
      throw new RuntimeException("read raw error",e);
    }
 else {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.e("read raw error");
      }
    }
  }
}
