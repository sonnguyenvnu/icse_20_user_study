@NotNull public static <K>SearchedObjects<K> union(SearchedObjects<K>... array){
  Set<K> searchedNodes=new LinkedHashSet<K>();
  for (  SearchedObjects<K> element : array) {
    searchedNodes.addAll(element.mySearchedNodes);
  }
  return new SearchedObjects<K>(searchedNodes);
}
