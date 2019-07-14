public void writeByteArray(byte[] b){
  try {
    if (b.length <= 253) {
      if (!justCalc) {
        buffer.put((byte)b.length);
      }
 else {
        len+=1;
      }
    }
 else {
      if (!justCalc) {
        buffer.put((byte)254);
        buffer.put((byte)b.length);
        buffer.put((byte)(b.length >> 8));
        buffer.put((byte)(b.length >> 16));
      }
 else {
        len+=4;
      }
    }
    if (!justCalc) {
      buffer.put(b);
    }
 else {
      len+=b.length;
    }
    int i=b.length <= 253 ? 1 : 4;
    while ((b.length + i) % 4 != 0) {
      if (!justCalc) {
        buffer.put((byte)0);
      }
 else {
        len+=1;
      }
      i++;
    }
  }
 catch (  Exception e) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("write byte array error");
    }
  }
}
