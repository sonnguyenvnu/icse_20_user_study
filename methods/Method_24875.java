public static List<Edit> fixFloatsRegex(CharSequence source){
  final List<Edit> edits=new ArrayList<>();
  Matcher matcher=NUMBER_LITERAL_REGEX.matcher(source);
  while (matcher.find()) {
    int offset=matcher.start();
    int end=matcher.end();
    String group=matcher.group().toLowerCase();
    boolean isFloatingPoint=group.contains(".") || group.contains("e");
    boolean hasSuffix=end < source.length() && Character.toLowerCase(source.charAt(end)) != 'f' && Character.toLowerCase(source.charAt(end)) != 'd';
    if (isFloatingPoint && !hasSuffix) {
      edits.add(Edit.insert(offset,"f"));
    }
  }
  return edits;
}
