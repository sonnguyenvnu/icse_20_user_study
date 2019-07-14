@Override default PK saveOrUpdate(E e){
  return createRequest("/").requestBody(JSON.toJSONString(e)).patch().as(getPrimaryKeyType());
}
