@SuppressWarnings("unchecked") public Object get(ServletContext sc,HttpServletRequest req,HttpServletResponse resp,Object refer){
  Class<?> clazz=Lang.getTypeClass(type);
  Map<String,Object> map;
  if (clazz.isInterface()) {
    map=new HashMap<String,Object>();
  }
 else {
    map=(Map<String,Object>)Mirror.me(type).born();
  }
  Enumeration<String> enu=(Enumeration<String>)req.getParameterNames();
  while (enu.hasMoreElements()) {
    String name=enu.nextElement();
    String[] vs=req.getParameterValues(name);
    if (null == vs || vs.length == 0) {
      map.put(name,null);
    }
 else     if (vs.length == 1) {
      map.put(name,vs[0]);
    }
 else {
      map.put(name,vs);
    }
  }
  return map;
}
