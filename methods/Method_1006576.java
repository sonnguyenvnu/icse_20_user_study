private static String bashify(CharSequence value){
  StringBuilder builder=new StringBuilder();
  for (int i=0; i < value.length(); i++) {
    char c=value.charAt(i);
    if (Character.isLetterOrDigit(c) || c == '_') {
      builder.append(c);
    }
 else     if (Character.isSpaceChar(c)) {
      builder.append('_');
    }
  }
  return builder.toString();
}
