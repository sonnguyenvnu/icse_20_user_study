@Override @Path("request/body/map") @POST @Produces(APPLICATION_JSON_VALUE) public User requestBodyMap(Map<String,Object> data,@QueryParam("param") String param){
  User user=new User();
  user.setId(((Integer)data.get("id")).longValue());
  user.setName((String)data.get("name"));
  user.setAge((Integer)data.get("age"));
  log("/request/body/map",param);
  return user;
}
