public Object get(ServletContext sc,HttpServletRequest req,HttpServletResponse resp,Object refer){
  if ("_map".equals(name)) {
    Map<String,String> headers=new LinkedHashMap<String,String>();
    Enumeration<String> names=req.getHeaderNames();
    while (names.hasMoreElements()) {
      String name=(String)names.nextElement();
      headers.put(name,req.getHeader(name));
    }
    return headers;
  }
  String val=req.getHeader(name);
  if (val == null) {
    Enumeration<String> names=req.getHeaderNames();
    while (names.hasMoreElements()) {
      String _name=(String)names.nextElement();
      if (_name.equalsIgnoreCase(name)) {
        val=req.getHeader(_name);
        break;
      }
    }
    if (val == null)     return null;
  }
  return Castors.me().castTo(val,type);
}
