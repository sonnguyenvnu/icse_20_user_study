@PostMapping(value="/request/body/user",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE) @Override public Map<String,Object> requestBodyUser(@RequestBody User user){
  Map<String,Object> map=new HashMap<>();
  map.put("id",user.getId());
  map.put("name",user.getName());
  map.put("age",user.getAge());
  return map;
}
