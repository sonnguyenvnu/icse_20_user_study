/** 
 * @deprecated
 */
public static String readAll(Reader reader){
  StringBuilder buf=new StringBuilder();
  try {
    char[] chars=new char[2048];
    for (; ; ) {
      int len=reader.read(chars,0,chars.length);
      if (len < 0) {
        break;
      }
      buf.append(chars,0,len);
    }
  }
 catch (  Exception ex) {
    throw new JSONException("read string from reader error",ex);
  }
  return buf.toString();
}
