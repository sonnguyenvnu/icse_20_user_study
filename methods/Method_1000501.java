public Object run(List<Object> fetchParam){
  if (fetchParam == null || fetchParam.isEmpty())   return System.currentTimeMillis();
  return new SimpleDateFormat(fetchParam.get(0).toString()).format(new Date());
}
