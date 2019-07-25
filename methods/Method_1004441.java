private void write(Object result,ServletResponse response){
  response.setContentType("application/json");
  response.setCharacterEncoding("UTF-8");
  try {
    String content=MAPPER.writeValueAsString(result);
    response.getWriter().write(content);
  }
 catch (  IOException e) {
    logger.error("write client failed");
  }
}
