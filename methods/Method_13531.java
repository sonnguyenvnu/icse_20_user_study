@Path("request/body/user") @POST @Override @Consumes(MediaType.APPLICATION_JSON) public Map<String,Object> requestBodyUser(User user){
  Map<String,Object> map=new HashMap<>();
  map.put("id",user.getId());
  map.put("name",user.getName());
  map.put("age",user.getAge());
  return map;
}
