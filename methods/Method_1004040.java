protected void process(HttpServletRequest request,HttpServletResponse response) throws IOException {
  int refresh=1200;
  if (request.getParameter("refresh") != null) {
    refresh=Integer.parseInt(request.getParameter("refresh"));
  }
  List<String> nodes=new ArrayList<>();
  for (  RemoteProxy proxy : getRegistry().getAllProxies()) {
    nodes.add(proxy.getHtmlRender().renderSummary());
  }
  int size=nodes.size();
  int rightColumnSize=size / 2;
  int leftColumnSize=size - rightColumnSize;
  StringBuilder leftColumnNodes=new StringBuilder();
  for (int i=0; i < leftColumnSize; i++) {
    leftColumnNodes.append(nodes.get(i));
  }
  StringBuilder rightColumnNodes=new StringBuilder();
  for (int i=leftColumnSize; i < nodes.size(); i++) {
    rightColumnNodes.append(nodes.get(i));
  }
  String hubConfigLinkVisible="";
  String hubConfigVisible="hidden";
  if (request.getParameter("config") != null) {
    hubConfigLinkVisible="hidden";
    hubConfigVisible="";
  }
  Map<String,String> consoleValues=new HashMap<>();
  consoleValues.put("{{refreshInterval}}",String.valueOf(refresh));
  consoleValues.put("{{coreVersion}}",coreVersion);
  consoleValues.put("{{leftColumnNodes}}",leftColumnNodes.toString());
  consoleValues.put("{{rightColumnNodes}}",rightColumnNodes.toString());
  consoleValues.put("{{unprocessedRequests}}",getUnprocessedRequests());
  consoleValues.put("{{requestQueue}}",getRequestQueue());
  consoleValues.put("{{hubConfigLinkVisible}}",hubConfigLinkVisible);
  consoleValues.put("{{hubConfigVisible}}",hubConfigVisible);
  consoleValues.put("{{hubConfig}}",getConfigInfo(request.getParameter("configDebug") != null));
  consoleValues.put("{{contextPath}}",contextPath);
  String renderTemplate=templateRenderer.renderTemplate(consoleValues);
  response.setContentType("text/html");
  response.setCharacterEncoding("UTF-8");
  response.setStatus(200);
  try (InputStream in=new ByteArrayInputStream(renderTemplate.getBytes("UTF-8"))){
    ByteStreams.copy(in,response.getOutputStream());
  }
  finally {
    response.getOutputStream().close();
  }
}
