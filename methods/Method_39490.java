public static String removeGenericsFromSignature(final String signature){
  final StringBuilder result=new StringBuilder(signature.length());
  int genericCount=0;
  int ndx=0;
  while (ndx != signature.length()) {
    final char c=signature.charAt(ndx);
    if (c == '<') {
      genericCount++;
      ndx++;
      continue;
    }
    if (c == '>') {
      genericCount--;
      ndx++;
      continue;
    }
    if (genericCount == 0) {
      result.append(c);
    }
    ndx++;
  }
  return result.toString();
}
