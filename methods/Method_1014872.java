private void out(ServletResponse response,Map<String,String> map) throws IOException {
  try {
    response.setCharacterEncoding("UTF-8");
    PrintWriter out=response.getWriter();
    out.println();
    out.close();
  }
 catch (  Exception e) {
    log.info("[KickoutSessionFilter.class ??JSON????????]-[{}]",new Date());
  }
}
