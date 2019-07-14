public String readString(boolean exception){
  int startReadPosition=getPosition();
  try {
    int sl=1;
    int l=getIntFromByte(buffer.get());
    if (l >= 254) {
      l=getIntFromByte(buffer.get()) | (getIntFromByte(buffer.get()) << 8) | (getIntFromByte(buffer.get()) << 16);
      sl=4;
    }
    byte[] b=new byte[l];
    buffer.get(b);
    int i=sl;
    while ((l + i) % 4 != 0) {
      buffer.get();
      i++;
    }
    return new String(b,"UTF-8");
  }
 catch (  Exception e) {
    if (exception) {
      throw new RuntimeException("read string error",e);
    }
 else {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.e("read string error");
      }
    }
    position(startReadPosition);
  }
  return "";
}
