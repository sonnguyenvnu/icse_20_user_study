/** 
 * Converts the full list of category discovery params into a grouped list of params. A group corresponds to a root category, and the list contains all subcategories.
 */
private static @NonNull List<List<DiscoveryParams>> paramsGroupedByRootCategory(final @NonNull List<DiscoveryParams> ps){
  final Map<String,List<DiscoveryParams>> grouped=new TreeMap<>();
  for (  final DiscoveryParams p : ps) {
    if (!grouped.containsKey(p.category().root().name())) {
      grouped.put(p.category().root().name(),new ArrayList<>());
    }
    grouped.get(p.category().root().name()).add(p);
  }
  return new ArrayList<>(grouped.values());
}
