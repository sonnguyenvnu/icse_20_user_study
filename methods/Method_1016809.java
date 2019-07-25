/** 
 * Replace all the HTML entities in string with their corresponding unicode characters.
 */
private String replace(String string){
  if (string == null) {
    return null;
  }
  string=string.replace(HTML_AMP,"&");
  Set<String> digits=new HashSet<>();
  Matcher digitsMatcher=PATTERN_HTML_ENTITY_DIGIT.matcher(string);
  while (digitsMatcher.find()) {
    digits.add(digitsMatcher.group());
  }
  if (digits.size() > 0) {
    for (    String digit : digits) {
      int codePoint=Integer.valueOf(digit.substring(2,digit.length() - 1));
      string=string.replace(digit,Character.toString((char)codePoint));
    }
  }
  Set<String> alphas=new HashSet<>();
  Matcher alphasMatcher=PATTERN_HTML_ENTITY_ALPHA.matcher(string);
  while (alphasMatcher.find()) {
    alphas.add(alphasMatcher.group());
  }
  if (alphas.size() > 0) {
    for (    String alpha : alphas) {
      String name=alpha.substring(1,alpha.length() - 1);
      string=string.replace(alpha,Character.toString((char)NAME_2_CODE_POINT.get(name).intValue()));
    }
  }
  return string;
}
