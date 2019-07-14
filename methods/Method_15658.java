@Override public AuthenticationBuilder json(String json){
  JSONObject jsonObject=JSON.parseObject(json);
  user(jsonObject.getObject("user",SimpleUser.class));
  role(jsonObject.getJSONArray("roles").toJSONString());
  permission(jsonObject.getJSONArray("permissions").toJSONString());
  return this;
}
