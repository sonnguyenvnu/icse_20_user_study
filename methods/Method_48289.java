public static boolean matches(KeyRangeQuery query,StaticBuffer key,StaticBuffer column){
  return matches(query,column) && query.getKeyStart().compareTo(key) <= 0 && query.getKeyEnd().compareTo(key) > 0;
}
