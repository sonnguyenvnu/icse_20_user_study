@Override public int updateBatch(Collection<E> data){
  assertNotNull(data);
  return data.stream().mapToInt(this::updateByPk).sum();
}
