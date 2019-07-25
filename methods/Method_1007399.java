private <T>T[] sort(T[] a){
  a=Arrays.copyOf(a,a.length);
  Arrays.sort(a,ToStringComparator.INSTANCE);
  return a;
}
