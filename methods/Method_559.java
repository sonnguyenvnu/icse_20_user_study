public final char next(){
  int index=++bp;
  if (index >= bufLength) {
    if (bufLength == -1) {
      return EOI;
    }
    if (sp > 0) {
      int offset;
      offset=bufLength - sp;
      if (ch == '"' && offset > 0) {
        offset--;
      }
      System.arraycopy(buf,offset,buf,0,sp);
    }
    np=-1;
    index=bp=sp;
    try {
      int startPos=bp;
      int readLength=buf.length - startPos;
      if (readLength == 0) {
        char[] newBuf=new char[buf.length * 2];
        System.arraycopy(buf,0,newBuf,0,buf.length);
        buf=newBuf;
        readLength=buf.length - startPos;
      }
      bufLength=reader.read(buf,bp,readLength);
    }
 catch (    IOException e) {
      throw new JSONException(e.getMessage(),e);
    }
    if (bufLength == 0) {
      throw new JSONException("illegal stat, textLength is zero");
    }
    if (bufLength == -1) {
      return ch=EOI;
    }
    bufLength+=bp;
  }
  return ch=buf[index];
}
