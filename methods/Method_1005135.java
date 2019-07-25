private static void check(HttpServletRequest request){
  if (ftl == null) {
synchronized (LOCK) {
      try {
        ftl=new FreemarkerUtil(request.getSession().getServletContext().getRealPath("/") + "export/template");
      }
 catch (      IOException e) {
        e.printStackTrace();
      }
    }
  }
}
