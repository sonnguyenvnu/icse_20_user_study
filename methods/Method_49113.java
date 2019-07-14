@Override public List<HasContainer> addLocalAll(Iterable<HasContainer> has){
  final List<HasContainer> containers=HasStepFolder.splitAndP(new ArrayList<>(),has);
  hasLocalContainers.put(containers,new QueryInfo(new ArrayList<>(),0,BaseQuery.NO_LIMIT));
  return containers;
}
