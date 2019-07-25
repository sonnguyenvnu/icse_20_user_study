@Override public String format(String text){
  String result=Objects.requireNonNull(text);
  if (result.isEmpty()) {
    return result;
  }
  for (  Map.Entry<String,String> unicodeLatexPair : HTMLUnicodeConversionMaps.UNICODE_LATEX_CONVERSION_MAP.entrySet()) {
    result=result.replace(unicodeLatexPair.getKey(),unicodeLatexPair.getValue());
  }
  StringBuilder sb=new StringBuilder();
  boolean consumed=false;
  for (int i=0; i <= (result.length() - 2); i++) {
    if (!consumed && (i < (result.length() - 1))) {
      int cpCurrent=result.codePointAt(i);
      Integer cpNext=result.codePointAt(i + 1);
      String code=HTMLUnicodeConversionMaps.ESCAPED_ACCENTS.get(cpNext);
      if (code == null) {
        sb.append((char)cpCurrent);
      }
 else {
        sb.append("{\\").append(code).append('{').append((char)cpCurrent).append("}}");
        consumed=true;
      }
    }
 else {
      consumed=false;
    }
  }
  if (!consumed) {
    sb.append((char)result.codePointAt(result.length() - 1));
  }
  result=sb.toString();
  for (int i=0; i <= (result.length() - 1); i++) {
    int cp=result.codePointAt(i);
    if (cp >= 129) {
      LOGGER.warn("Unicode character not converted: " + cp);
    }
  }
  return result;
}
