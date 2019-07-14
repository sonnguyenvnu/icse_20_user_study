@NotNull public static String arrayToSortedStringSet(Collection<String> strings){
  Set<String> sorter=new TreeSet<>();
  sorter.addAll(strings);
  return arrayToString(sorter);
}
