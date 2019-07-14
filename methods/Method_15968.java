@Override default int updateByPk(List<E> data){
  return createRequest("/batch").requestBody(JSON.toJSONString(data)).put().as(Integer.class);
}
