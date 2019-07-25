public static String format(String s){
  if (indentStr.length() == 0)   return s;
  int i=s.indexOf('\n');
  if (i >= 0) {
    StringBuffer sb=new StringBuffer(100);
    sb.append(indentStr);
    int prev=0;
    do {
      sb.append(s,prev,i + 1);
      sb.append(indentStr);
      prev=i + 1;
      if (prev >= s.length())       break;
      i=s.indexOf('\n',prev);
    }
 while (i != -1);
    sb.append(s,prev,s.length());
    return sb.toString();
  }
 else {
    return indentStr + s;
  }
}
