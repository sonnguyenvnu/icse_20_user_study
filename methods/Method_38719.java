private static <V>Set<V> set(final int size,final V[] array){
  int index=0;
  final Set<V> set=new HashSet<>();
  for (  final V v : array) {
    set.add(v);
    index++;
    if (index == size) {
      break;
    }
  }
  return set;
}
