public static String escape(String regex){
  if (regex == null || regex.isEmpty()) {
    return regex;
  }
  char[] chars=regex.toCharArray();
  StringBuilder builder=new StringBuilder();
  for (  char aChar : chars) {
    if (SPECIAL_WORDS.contains(aChar)) {
      builder.append('\\');
    }
    builder.append(aChar);
  }
  return builder.toString();
}
