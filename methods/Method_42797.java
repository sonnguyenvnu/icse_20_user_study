public String readRequest(HttpServletRequest request) throws IOException {
  StringBuilder sb=new StringBuilder();
  try {
    String line;
    while ((line=request.getReader().readLine()) != null) {
      sb.append(line);
    }
  }
  finally {
    request.getReader().close();
  }
  return sb.toString();
}
