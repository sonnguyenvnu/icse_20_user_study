public static String quote(final String term){
  return new String(quote(term.getBytes(UTF_8)),UTF_8);
}
