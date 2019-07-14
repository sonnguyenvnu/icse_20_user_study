static String keywordToUpperCase(String text){
  if (!containsLowerCase(text)) {
    return text;
  }
  StringBuilder result=new StringBuilder(text.length());
  for (int i=0; i < text.length(); i++) {
    char c=text.charAt(i);
    if (c >= 'a' && c <= 'z') {
      result.append((char)(c - ('a' - 'A')));
    }
 else {
      result.append(c);
    }
  }
  return result.toString();
}
