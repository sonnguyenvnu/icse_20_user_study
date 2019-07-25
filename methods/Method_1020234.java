@RequestMapping("/test") public IMoocJSONResult test(){
  strRedis.opsForValue().set("imooc-cache","hello ???~~~~~~");
  SysUser user=new SysUser();
  user.setId("100111");
  user.setUsername("imooc");
  user.setPassword("abc123");
  user.setIsDelete(0);
  user.setRegistTime(new Date());
  strRedis.opsForValue().set("json:user",JsonUtils.objectToJson(user));
  SysUser jsonUser=JsonUtils.jsonToPojo(strRedis.opsForValue().get("json:user"),SysUser.class);
  return IMoocJSONResult.ok(jsonUser);
}
