private void response(ServletResponse resp) throws IOException {
  resp.setContentType("application/json");
  RecordQueryResult result=new RecordQueryResult(Collections.emptyList());
  resp.getWriter().println(serializer.serialize(result));
}
