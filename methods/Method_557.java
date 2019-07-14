public final char charAt(int index){
  if (index >= bufLength) {
    if (bufLength == -1) {
      if (index < sp) {
        return buf[index];
      }
      return EOI;
    }
    if (bp == 0) {
      char[] buf=new char[(this.buf.length * 3) / 2];
      System.arraycopy(this.buf,bp,buf,0,bufLength);
      int rest=buf.length - bufLength;
      try {
        int len=reader.read(buf,bufLength,rest);
        bufLength+=len;
        this.buf=buf;
      }
 catch (      IOException e) {
        throw new JSONException(e.getMessage(),e);
      }
    }
 else {
      int rest=bufLength - bp;
      if (rest > 0) {
        System.arraycopy(buf,bp,buf,0,rest);
      }
      try {
        bufLength=reader.read(buf,rest,buf.length - rest);
      }
 catch (      IOException e) {
        throw new JSONException(e.getMessage(),e);
      }
      if (bufLength == 0) {
        throw new JSONException("illegal state, textLength is zero");
      }
      if (bufLength == -1) {
        return EOI;
      }
      bufLength+=rest;
      index-=bp;
      np-=bp;
      bp=0;
    }
  }
  return buf[index];
}
