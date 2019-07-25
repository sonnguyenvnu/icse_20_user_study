static boolean and(final List<Character> cs){
  return cs.length() == 3 && cs.head() == '0' && (cs.tail().head() != '0' || cs.tail().tail().head() != '0');
}
