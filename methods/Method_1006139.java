private static String normalize(String content){
  List<String> tokens=new ArrayList<>();
  int b=0;
  StringBuilder and=new StringBuilder();
  StringBuilder token=new StringBuilder();
  for (int p=0; p < content.length(); p++) {
    if (b == 0) {
      String andString=and.toString();
      if (((andString.isEmpty()) && (content.charAt(p) == ' ')) || (" ".equals(andString) && (content.charAt(p) == 'a')) || (" a".equals(andString) && (content.charAt(p) == 'n')) || (" an".equals(andString) && (content.charAt(p) == 'd'))) {
        and.append(content.charAt(p));
      }
 else       if (" and".equals(and.toString()) && (content.charAt(p) == ' ')) {
        and=new StringBuilder();
        tokens.add(token.toString().trim());
        token=new StringBuilder();
      }
 else {
        if (content.charAt(p) == '{') {
          b++;
        }
        if (content.charAt(p) == '}') {
          b--;
        }
        token.append(and);
        and=new StringBuilder();
        token.append(content.charAt(p));
      }
    }
 else {
      token.append(content.charAt(p));
    }
  }
  tokens.add(token.toString());
  StringBuilder normalized=new StringBuilder("");
  for (int i=0; i < tokens.size(); i++) {
    if (i > 0) {
      normalized.append(" and ");
    }
    normalized.append(isInstitution(tokens.get(i)) ? generateInstitutionKey(tokens.get(i)) : removeDiacritics(tokens.get(i)));
  }
  return normalized.toString();
}
