private void render(HttpServletResponse response,ResultStatus cm) throws Exception {
  response.setContentType("application/json;charset=UTF-8");
  OutputStream out=response.getOutputStream();
  String str=JSON.toJSONString(ResultGeekQ.error(cm));
  out.write(str.getBytes("UTF-8"));
  out.flush();
  out.close();
}
