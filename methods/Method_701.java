public void writeFieldValue(char seperator,String name,boolean value){
  if (!quoteFieldNames) {
    write(seperator);
    writeFieldName(name);
    write(value);
    return;
  }
  int intSize=value ? 4 : 5;
  int nameLen=name.length();
  int newcount=count + nameLen + 4 + intSize;
  if (newcount > buf.length) {
    if (writer != null) {
      write(seperator);
      writeString(name);
      write(':');
      write(value);
      return;
    }
    expandCapacity(newcount);
  }
  int start=count;
  count=newcount;
  buf[start]=seperator;
  int nameEnd=start + nameLen + 1;
  buf[start + 1]=keySeperator;
  name.getChars(0,nameLen,buf,start + 2);
  buf[nameEnd + 1]=keySeperator;
  if (value) {
    System.arraycopy(":true".toCharArray(),0,buf,nameEnd + 2,5);
  }
 else {
    System.arraycopy(":false".toCharArray(),0,buf,nameEnd + 2,6);
  }
}
