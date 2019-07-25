private static String describe(Multimap<String,String> cpuInfo,String s){
  Collection<String> strings=cpuInfo.get(s);
  return (strings.size() == 1) ? strings.iterator().next() : ImmutableMultiset.copyOf(strings).toString();
}
