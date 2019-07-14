@Override public List<PK> insertBatch(Collection<E> data){
  return data.stream().map(this::insert).collect(Collectors.toList());
}
