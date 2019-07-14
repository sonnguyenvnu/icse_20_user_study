public static List<Edit> replaceTypeConstructors(CharSequence source){
  List<Edit> result=new ArrayList<>();
  Matcher matcher=TYPE_CONSTRUCTOR_REGEX.matcher(source);
  while (matcher.find()) {
    String match=matcher.group(1);
    int offset=matcher.start(1);
    int length=match.length();
    result.add(Edit.insert(offset,"PApplet."));
    String replace="parse" + Character.toUpperCase(match.charAt(0)) + match.substring(1);
    result.add(Edit.replace(offset,length,replace));
  }
  return result;
}
