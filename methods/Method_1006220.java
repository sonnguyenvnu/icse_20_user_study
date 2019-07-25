@Override public String format(String fieldText){
  StringBuilder builder=new StringBuilder(fieldText.length());
  for (  char c : fieldText.toCharArray()) {
    if (c == '}') {
      builder.append(',');
    }
 else     if (c != '{') {
      builder.append(c);
    }
  }
  return builder.toString();
}
