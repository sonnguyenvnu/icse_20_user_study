public FindRequestBuilder<K,V> name(String name){
  setParam(RestConstants.QUERY_TYPE_PARAM,name);
  _name=name;
  return this;
}
