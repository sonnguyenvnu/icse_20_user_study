private static List<InetSocketAddress> convert(Set<String> source){
  final List<InetSocketAddress> seeds=new ArrayList<>();
  for (  final String s : source) {
    seeds.add(convert(s));
  }
  if (seeds.isEmpty()) {
    throw new IllegalArgumentException("No seeds specified");
  }
  return seeds;
}
