public MultiKeySliceQuery getQuery(final CompositeIndexType index,List<Object[]> values){
  final List<KeySliceQuery> ksqs=new ArrayList<>(values.size());
  for (  final Object[] value : values) {
    ksqs.add(new KeySliceQuery(getIndexKey(index,value),BufferUtil.zeroBuffer(1),BufferUtil.oneBuffer(1)));
  }
  return new MultiKeySliceQuery(ksqs);
}
