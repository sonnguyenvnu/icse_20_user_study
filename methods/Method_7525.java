public void readBytes(byte[] b,int offset,int count,boolean exception){
  try {
    buffer.get(b,offset,count);
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
