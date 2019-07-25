public Object get(ServletContext sc,HttpServletRequest req,HttpServletResponse resp,Object refer){
  try {
    return req.getInputStream();
  }
 catch (  IOException e) {
    throw Lang.wrapThrow(e);
  }
}
