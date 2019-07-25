public BObject parse(){
  if (pos >= b.length)   return null;
  char ch=(char)b[pos];
  if ((ch >= '0') && (ch <= '9')) {
    int end=pos;
    end++;
    while (b[end] != ':')     ++end;
    final int len=Integer.parseInt(ASCII.String(b,pos,end - pos));
    final byte[] s=new byte[len];
    System.arraycopy(b,end + 1,s,0,len);
    pos=end + len + 1;
    return new BStringObject(s);
  }
 else   if (ch == 'l') {
    pos++;
    return new BListObject(readList());
  }
 else   if (ch == 'd') {
    pos++;
    return new BDictionaryObject(convertToMap(readList()));
  }
 else   if (ch == 'i') {
    pos++;
    int end=pos;
    while (b[end] != 'e')     ++end;
    BIntegerObject io=new BIntegerObject(Long.parseLong(UTF8.String(b,pos,end - pos)));
    pos=end + 1;
    return io;
  }
 else {
    return null;
  }
}
