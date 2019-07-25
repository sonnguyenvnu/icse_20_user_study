@SuppressWarnings("WeakerAccess") protected void process(HttpServletRequest request,HttpServletResponse response) throws IOException {
  String refresh="1200";
  String testBuild="";
  try {
    refresh=Optional.ofNullable(request.getParameter("refresh")).orElse(refresh);
    testBuild=Optional.ofNullable(request.getParameter("build")).orElse(testBuild);
  }
 catch (  Exception e) {
    LOGGER.debug(e.toString(),e);
  }
  List<String> nodes=new ArrayList<>();
  for (  RemoteProxy proxy : getRegistry().getAllProxies()) {
    if (proxy instanceof DockerSeleniumRemoteProxy) {
      DockerSeleniumRemoteProxy dockerSeleniumRemoteProxy=(DockerSeleniumRemoteProxy)proxy;
      HtmlRenderer renderer=new LiveNodeHtmlRenderer(dockerSeleniumRemoteProxy);
      if (testBuild.isEmpty() || testBuild.equalsIgnoreCase(dockerSeleniumRemoteProxy.getTestBuild())) {
        nodes.add(renderer.renderSummary());
      }
    }
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
  Map<String,String> livePreviewValues=new HashMap<>();
  livePreviewValues.put("{{refreshInterval}}",refresh);
  livePreviewValues.put("{{leftColumnNodes}}",leftColumnNodes.toString());
  livePreviewValues.put("{{rightColumnNodes}}",rightColumnNodes.toString());
  livePreviewValues.put("{{contextPath}}",contextPath);
  String templateFile="html_templates/live_preview_servlet.html";
  TemplateRenderer templateRenderer=new TemplateRenderer(templateFile);
  String renderTemplate=templateRenderer.renderTemplate(livePreviewValues);
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
