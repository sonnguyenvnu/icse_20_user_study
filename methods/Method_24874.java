public static List<Edit> replaceColorRegex(CharSequence source){
  final List<Edit> edits=new ArrayList<>();
  Matcher matcher=COLOR_TYPE_REGEX.matcher(source);
  while (matcher.find()) {
    int offset=matcher.start(1);
    edits.add(Edit.replace(offset,5,"int"));
  }
  return edits;
}
