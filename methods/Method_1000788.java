public Object get(ServletContext sc,HttpServletRequest req,HttpServletResponse resp,Object refer){
  if (refer == null)   return null;
  Object obj=((Map<?,?>)refer).get(name);
  if (obj == null)   return null;
  if (obj instanceof List) {
    return Lang.collection2array((List<?>)((List<?>)obj));
  }
  Object re=Array.newInstance(eleType,1);
  Array.set(re,0,obj);
  return re;
}
