/** 
 * Fills header and footer.
 * @param context   the specified request context
 * @param dataModel the specified data model
 */
public void fillHeaderAndFooter(final RequestContext context,final Map<String,Object> dataModel){
  Stopwatchs.start("Fills header");
  try {
    fillHeader(context,dataModel);
  }
  finally {
    Stopwatchs.end();
  }
  Stopwatchs.start("Fills footer");
  try {
    fillFooter(dataModel);
  }
  finally {
    Stopwatchs.end();
  }
  final String serverScheme=Latkes.getServerScheme();
  dataModel.put(Common.WEBSOCKET_SCHEME,StringUtils.containsIgnoreCase(serverScheme,"https") ? "wss" : "ws");
  dataModel.put(Common.MARKDOWN_HTTP_AVAILABLE,Markdowns.MARKDOWN_HTTP_AVAILABLE);
}
