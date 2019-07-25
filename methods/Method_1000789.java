@Override public Object get(ServletContext sc,HttpServletRequest req,HttpServletResponse resp,Object refer){
  if (null != refer)   if (refer instanceof Map<?,?>) {
    Object value=((Map<?,?>)refer).get(name);
    return Castors.me().castTo(value,klass);
  }
  return null;
}
