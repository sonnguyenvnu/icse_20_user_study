@NotNull public static String replace(@NotNull String text,@NotNull String from,@NotNull String to){
  final StringBuilder result=new StringBuilder(text.length());
  final int len=from.length();
  for (int i=0; i < text.length(); i++) {
    if (text.regionMatches(i,from,0,len)) {
      result.append(to);
      i+=len - 1;
      continue;
    }
    result.append(text.charAt(i));
  }
  return result.toString();
}
