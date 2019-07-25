public static <K,V>boolean equals(IMap<K,V> a,IMap<K,V> b,BiPredicate<V,V> valEquals){
  if (a.size() != b.size()) {
    return false;
  }
 else   if (a == b) {
    return true;
  }
  return a.entries().stream().allMatch(e -> {
    IMap m=b;
    Object val=m.get(e.key(),DEFAULT_VALUE);
    return val != DEFAULT_VALUE && valEquals.test((V)val,e.value());
  }
);
}
