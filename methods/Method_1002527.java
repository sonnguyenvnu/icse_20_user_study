public BatchFindRequestBuilder<K,V> name(String name){
  setParam(RestConstants.BATCH_FINDER_QUERY_TYPE_PARAM,name);
  _name=name;
  return this;
}
