public static List<Edit> replaceHexLiterals(CharSequence source){
  List<Edit> result=new ArrayList<>();
  Matcher matcher=HEX_LITERAL_REGEX.matcher(source);
  while (matcher.find()) {
    int offset=matcher.start(1);
    result.add(Edit.replace(offset,1,"0xff"));
  }
  return result;
}
