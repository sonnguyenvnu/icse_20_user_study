public void write(HttpServletResponse response,String s){
  PrintWriter out=null;
  try {
    out=response.getWriter();
    out.print(s);
  }
 catch (  IOException e) {
    log.error("?????????????????",e);
  }
 finally {
    out.close();
  }
}
