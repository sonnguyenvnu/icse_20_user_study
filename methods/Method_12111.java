public static void out(HttpServletResponse res,String html){
  res.setContentType("text/html");
  res.setCharacterEncoding("UTF-8");
  try {
    OutputStream out=res.getOutputStream();
    out.write(html.getBytes("UTF-8"));
    out.flush();
    out.close();
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
