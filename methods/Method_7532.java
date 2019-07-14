public void writeByteArray(byte[] b){
  try {
    if (b.length <= 253) {
      if (!justCalc) {
        out.write(b.length);
      }
 else {
        len+=1;
      }
    }
 else {
      if (!justCalc) {
        out.write(254);
        out.write(b.length);
        out.write(b.length >> 8);
        out.write(b.length >> 16);
      }
 else {
        len+=4;
      }
    }
    if (!justCalc) {
      out.write(b);
    }
 else {
      len+=b.length;
    }
    int i=b.length <= 253 ? 1 : 4;
    while ((b.length + i) % 4 != 0) {
      if (!justCalc) {
        out.write(0);
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
