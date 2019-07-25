@Override public void send(HttpServletResponse resp) throws IOException {
  output.setRequestId(Config.getRequestId());
  resp.setStatus(getStatusCode());
  resp.setContentType("application/json");
  PrintWriter pw=resp.getWriter();
  pw.print(JsonUtils.toJson(output));
}
