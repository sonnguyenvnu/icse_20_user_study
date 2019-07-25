public static NutMvcContext ctx(){
  ServletContext sc=getServletContext();
  if (sc == null) {
    if (ctx == null)     ctx=new NutMvcContext();
    return ctx;
  }
  NutMvcContext c=(NutMvcContext)getServletContext().getAttribute("__nutz__mvc__ctx");
  if (c == null) {
    c=new NutMvcContext();
    getServletContext().setAttribute("__nutz__mvc__ctx",c);
    ctx=c;
  }
  return c;
}
