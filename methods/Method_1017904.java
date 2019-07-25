public List<T> save(List<E> values){
  List<T> dtoList=values.stream().map(v -> transformEntityToDto(v)).collect(Collectors.toList());
  dtoList.forEach(dto -> addItem(dto));
  clusterService.sendMessageToOthers(getClusterMessageKey(),new CacheBackedProviderClusterMessage(CacheBackedProviderClusterMessage.Type.ADDED,Lists.newArrayList(dtoList)));
  return dtoList;
}
