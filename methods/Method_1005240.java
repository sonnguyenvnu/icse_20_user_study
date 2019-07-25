@Override public Object execute(String paramJson){
  String prefix="CN";
  if (paramJson != null && !"".equals(paramJson)) {
    JSONObject jsonObject=JSONObject.fromObject(paramJson);
    Object obj=jsonObject.get("prefix");
    if (obj != null)     prefix=obj.toString();
  }
  SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
  int random=RandomUtils.nextInt(90) + 10;
  return prefix + format.format(new Date()) + random;
}
