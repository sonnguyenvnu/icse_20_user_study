public void writeLong(long i){
  boolean needQuotationMark=isEnabled(SerializerFeature.BrowserCompatible) && (!isEnabled(SerializerFeature.WriteClassName)) && (i > 9007199254740991L || i < -9007199254740991L);
  if (i == Long.MIN_VALUE) {
    if (needQuotationMark) {
      write("\"-9223372036854775808\"");
    }
 else {
      write("-9223372036854775808");
    }
    return;
  }
  int size=(i < 0) ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);
  int newcount=count + size;
  if (needQuotationMark)   newcount+=2;
  if (newcount > buf.length) {
    if (writer == null) {
      expandCapacity(newcount);
    }
 else {
      char[] chars=new char[size];
      IOUtils.getChars(i,size,chars);
      if (needQuotationMark) {
        write('"');
        write(chars,0,chars.length);
        write('"');
      }
 else {
        write(chars,0,chars.length);
      }
      return;
    }
  }
  if (needQuotationMark) {
    buf[count]='"';
    IOUtils.getChars(i,newcount - 1,buf);
    buf[newcount - 1]='"';
  }
 else {
    IOUtils.getChars(i,newcount,buf);
  }
  count=newcount;
}
