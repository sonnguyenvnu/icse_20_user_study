public synchronized void load(Reader reader,boolean clear) throws IOException {
  if (clear)   this.clear();
  BufferedReader tr=null;
  if (reader instanceof BufferedReader)   tr=(BufferedReader)reader;
 else   tr=new BufferedReader(reader);
  String s;
  while (null != (s=tr.readLine())) {
    if (Strings.isBlank(s))     continue;
    if (s.length() > 0 && s.trim().charAt(0) == '#')     continue;
    int pos;
    char c='0';
    for (pos=0; pos < s.length(); pos++) {
      c=s.charAt(pos);
      if (c == '=' || c == ':')       break;
    }
    if (c == '=') {
      String name=s.substring(0,pos);
      String value=s.substring(pos + 1);
      if (value.endsWith("\\") && !value.endsWith("\\\\")) {
        StringBuilder sb=new StringBuilder(value.substring(0,value.length() - 1));
        while (null != (s=tr.readLine())) {
          if (Strings.isBlank(s))           break;
          if (s.endsWith("\\") && !s.endsWith("\\\\")) {
            sb.append(s.substring(0,s.length() - 1));
          }
 else {
            sb.append(s);
            break;
          }
        }
        value=sb.toString();
      }
      if (value.contains("\\u")) {
        value=Strings.unicodeDecode(value);
      }
      value=value.replace("\\:",":").replace("\\=","=");
      maps.put(Strings.trim(name),value);
    }
 else     if (c == ':') {
      String name=s.substring(0,pos);
      StringBuffer sb=new StringBuffer();
      sb.append(s.substring(pos + 1));
      String ss;
      while (null != (ss=tr.readLine())) {
        if (ss.length() > 0 && ss.charAt(0) == '#')         break;
        sb.append("\r\n" + ss);
      }
      maps.put(Strings.trim(name),sb.toString());
      if (null == ss)       return;
    }
 else {
      maps.put(Strings.trim(s),"");
    }
  }
}
