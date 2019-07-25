@Override public String format(String fieldText){
  StringBuilder builder=new StringBuilder(fieldText.length());
  for (  char c : fieldText.toCharArray()) {
    if ((c != '{') && (c != '}')) {
      builder.append(c);
    }
  }
  return builder.toString();
}
