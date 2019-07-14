public void writeFieldValue(char seperator,String name,int value){
  if (value == Integer.MIN_VALUE || !quoteFieldNames) {
    write(seperator);
    writeFieldName(name);
    writeInt(value);
    return;
  }
  int intSize=(value < 0) ? IOUtils.stringSize(-value) + 1 : IOUtils.stringSize(value);
  int nameLen=name.length();
  int newcount=count + nameLen + 4 + intSize;
  if (newcount > buf.length) {
    if (writer != null) {
      write(seperator);
      writeFieldName(name);
      writeInt(value);
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
  buf[nameEnd + 2]=':';
  IOUtils.getChars(value,count,buf);
}
