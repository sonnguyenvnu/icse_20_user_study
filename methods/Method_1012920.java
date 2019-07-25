@Override public void send(HttpServletRequest req,HttpServletResponse resp) throws IOException {
  resp.setContentType("text/csv; charset=UTF-8");
  resp.setHeader("Content-Disposition",getContentDispositionHeader());
  PrintWriter writer=resp.getWriter();
  writer.write("\uFEFF");
  writer.append(fileContent);
}
