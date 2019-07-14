public String readString(boolean exception){
  try {
    int sl=1;
    int l=in.read();
    len++;
    if (l >= 254) {
      l=in.read() | (in.read() << 8) | (in.read() << 16);
      len+=3;
      sl=4;
    }
    byte[] b=new byte[l];
    in.read(b);
    len++;
    int i=sl;
    while ((l + i) % 4 != 0) {
      in.read();
      len++;
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
  }
  return null;
}
