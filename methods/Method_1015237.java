public static <V>boolean equals(ISet<V> a,ISet<V> b){
  if (a.size() != b.size()) {
    return false;
  }
 else   if (a == b) {
    return true;
  }
  return a.elements().stream().allMatch(b::contains);
}
