@Override default int updateByPk(PK id,E data){
  return createRequest("/" + id).requestBody(JSON.toJSONString(data)).put().as(Integer.class);
}
