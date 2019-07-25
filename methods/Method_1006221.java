@Override public String format(String fieldText){
  StringBuilder result=new StringBuilder(fieldText.length());
  char[] c=fieldText.toCharArray();
  for (int i=0; i < c.length; i++) {
    if (c[i] == '~') {
      result.append(' ');
    }
 else {
      result.append(c[i]);
      if ((c[i] == '\\') && ((i + 1) < c.length)) {
        i++;
        result.append(c[i]);
      }
    }
  }
  return result.toString();
}
