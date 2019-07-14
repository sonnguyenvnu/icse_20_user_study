private static void validateLocalePart(String localePart){
  for (int i=0; i < localePart.length(); i++) {
    char ch=localePart.charAt(i);
    if (ch != ' ' && ch != '_' && ch != '#' && !Character.isLetterOrDigit(ch)) {
      throw new IllegalArgumentException("Locale part \"" + localePart + "\" contains invalid characters");
    }
  }
}
