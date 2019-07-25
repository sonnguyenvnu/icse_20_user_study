public String check(String key){
  String val=get(key);
  if (null == val)   throw Lang.makeThrow("Ioc.$conf expect property '%s'",key);
  return val;
}
