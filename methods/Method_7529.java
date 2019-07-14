public NativeByteBuffer readByteBuffer(boolean exception){
  try {
    int sl=1;
    int l=getIntFromByte(buffer.get());
    if (l >= 254) {
      l=getIntFromByte(buffer.get()) | (getIntFromByte(buffer.get()) << 8) | (getIntFromByte(buffer.get()) << 16);
      sl=4;
    }
    NativeByteBuffer b=new NativeByteBuffer(l);
    int old=buffer.limit();
    buffer.limit(buffer.position() + l);
    b.buffer.put(buffer);
    buffer.limit(old);
    b.buffer.position(0);
    int i=sl;
    while ((l + i) % 4 != 0) {
      buffer.get();
      i++;
    }
    return b;
  }
 catch (  Exception e) {
    if (exception) {
      throw new RuntimeException("read byte array error",e);
    }
 else {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.e("read byte array error");
      }
    }
  }
  return null;
}
