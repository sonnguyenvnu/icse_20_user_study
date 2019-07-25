public static <V>IList<V> reverse(IList<V> l){
  return Lists.from(l.size(),i -> l.nth(l.size() - (i + 1)));
}
