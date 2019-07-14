@Override public Object lookup(final String name){
  HttpSession session=getCurrentHttpSession();
  Map<String,BeanData> map=getSessionMap(session);
  if (map == null) {
    return null;
  }
  BeanData beanData=map.get(name);
  if (beanData == null) {
    return null;
  }
  return beanData.bean();
}
