public static String arrayToSortedStringSet(Collection<String> strings){
  Set<String> sorter=new TreeSet<String>();
  sorter.addAll(strings);
  return arrayToString(sorter);
}
