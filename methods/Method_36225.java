@Override public ResponseDefinition render(Admin admin,Request request){
  LoggedRequest loggedRequest=LoggedRequest.createFrom(request.getOriginalRequest().or(request));
  List<NearMiss> nearMisses=admin.findTopNearMissesFor(loggedRequest).getNearMisses();
  Map<String,RequestMatcherExtension> customMatcherExtensions=admin.getOptions().extensionsOfType(RequestMatcherExtension.class);
  PlainTextDiffRenderer diffRenderer=loggedRequest.containsHeader(CONSOLE_WIDTH_HEADER_KEY) ? new PlainTextDiffRenderer(customMatcherExtensions,Integer.parseInt(loggedRequest.getHeader(CONSOLE_WIDTH_HEADER_KEY))) : new PlainTextDiffRenderer(customMatcherExtensions);
  String body;
  if (nearMisses.isEmpty()) {
    body="No response could be served as there are no stub mappings in this WireMock instance.";
  }
 else {
    Diff firstDiff=nearMisses.get(0).getDiff();
    body=diffRenderer.render(firstDiff);
  }
  return ResponseDefinitionBuilder.responseDefinition().withStatus(404).withHeader(CONTENT_TYPE,"text/plain").withBody(body).build();
}
