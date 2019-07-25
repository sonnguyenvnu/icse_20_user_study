public Object get(ServletContext sc,HttpServletRequest req,HttpServletResponse resp,Object refer){
  if (refer == null)   return null;
  TempFile tmp=getTempFile(refer,name);
  if (tmp == null)   return null;
  try {
    return Streams.buffr(new InputStreamReader(tmp.getInputStream()));
  }
 catch (  IOException e) {
    throw Lang.wrapThrow(e);
  }
}
