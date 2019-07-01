protected <T>Map<String,T> _XXXXX_(ApplicationContext applicationContext,Class<T> clazz){
  Map<String,T> springBeans=applicationContext.getBeansOfType(clazz);
  Map<String,T> beans=new HashMap<>(springBeans.size());
  for (  Map.Entry<String,T> entry : springBeans.entrySet()) {
    String key=StringUtils.contains(entry.getKey(),'#') ? StringUtils.substringAfterLast(entry.getKey(),"#") : entry.getKey();
    beans.put(key,entry.getValue());
  }
  return beans;
}