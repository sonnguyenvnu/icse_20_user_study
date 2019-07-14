public final char[] sub_chars(int offset,int count){
  if (ASMUtils.IS_ANDROID && count < sbuf.length) {
    text.getChars(offset,offset + count,sbuf,0);
    return sbuf;
  }
 else {
    char[] chars=new char[count];
    text.getChars(offset,offset + count,chars,0);
    return chars;
  }
}
