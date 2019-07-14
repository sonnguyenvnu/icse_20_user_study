@Override default PK insert(E data){
  return createRequest("/").requestBody(JSON.toJSONString(data)).post().as(getPrimaryKeyType());
}
