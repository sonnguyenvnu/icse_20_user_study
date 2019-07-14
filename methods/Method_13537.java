@Override @PostMapping(value="/request/body/map",produces=MediaType.APPLICATION_JSON_UTF8_VALUE) public User requestBodyMap(@RequestBody Map<String,Object> data,@RequestParam("param") String param){
  User user=new User();
  user.setId(((Integer)data.get("id")).longValue());
  user.setName((String)data.get("name"));
  user.setAge((Integer)data.get("age"));
  log("/request/body/map",param);
  return user;
}
