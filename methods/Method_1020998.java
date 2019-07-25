@Override public Object deserialize(byte[] bytes){
  if (bytes == null || bytes.length == 0)   return null;
  String json=new String(bytes);
  JSONObject jsonObject=JSON.parseObject(json);
  Class clazz=null;
  try {
    clazz=Class.forName(jsonObject.getString("clazz"));
  }
 catch (  ClassNotFoundException e) {
    LOG.error(e.toString(),e);
    return null;
  }
  return jsonObject.getObject("object",clazz);
}
