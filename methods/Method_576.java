public final String subString(int offset,int count){
  if (ASMUtils.IS_ANDROID) {
    if (count < sbuf.length) {
      text.getChars(offset,offset + count,sbuf,0);
      return new String(sbuf,0,count);
    }
 else {
      char[] chars=new char[count];
      text.getChars(offset,offset + count,chars,0);
      return new String(chars);
    }
  }
 else {
    return text.substring(offset,offset + count);
  }
}
