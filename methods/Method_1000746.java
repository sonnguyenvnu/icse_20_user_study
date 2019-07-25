public Object get(ServletContext sc,HttpServletRequest req,HttpServletResponse resp,Object refer){
  try {
    return req.getReader();
  }
 catch (  IOException e) {
    throw Lang.wrapThrow(e);
  }
}
