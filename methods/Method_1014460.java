@Override protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
  logger.debug("Servlet request received!");
  String sitemapName=req.getParameter("sitemap");
  String widgetId=req.getParameter("w");
  String subscriptionId=req.getParameter("subscriptionId");
  boolean async="true".equalsIgnoreCase(req.getParameter("__async"));
  if (sitemapName == null) {
    sitemapName=config.getDefaultSitemap();
  }
  StringBuilder result=new StringBuilder();
  Sitemap sitemap=null;
  for (  SitemapProvider sitemapProvider : sitemapProviders) {
    sitemap=sitemapProvider.getSitemap(sitemapName);
    if (sitemap != null) {
      break;
    }
  }
  try {
    if (sitemap == null) {
      showSitemapList(res);
      return;
    }
    logger.debug("reading sitemap {}",sitemap.getName());
    if (widgetId == null || widgetId.isEmpty() || widgetId.equals(sitemapName)) {
      if (subscriptionId != null) {
        if (subscriptions.exists(subscriptionId)) {
          subscriptions.setPageId(subscriptionId,sitemap.getName(),sitemapName);
        }
 else {
          logger.debug("Basic UI requested a non-existing event subscription id ({})",subscriptionId);
        }
      }
      String label=sitemap.getLabel() != null ? sitemap.getLabel() : sitemapName;
      EList<Widget> children=renderer.getItemUIRegistry().getChildren(sitemap);
      result.append(renderer.processPage(sitemapName,sitemapName,label,children,async));
    }
 else     if (!widgetId.equals("Colorpicker")) {
      if (subscriptionId != null) {
        if (subscriptions.exists(subscriptionId)) {
          subscriptions.setPageId(subscriptionId,sitemap.getName(),widgetId);
        }
 else {
          logger.debug("Basic UI requested a non-existing event subscription id ({})",subscriptionId);
        }
      }
      Widget w=renderer.getItemUIRegistry().getWidget(sitemap,widgetId);
      if (w != null) {
        String label=renderer.getItemUIRegistry().getLabel(w);
        if (label == null) {
          label="undefined";
        }
        if (!(w instanceof LinkableWidget)) {
          throw new RenderException("Widget '" + w + "' can not have any content");
        }
        EList<Widget> children=renderer.getItemUIRegistry().getChildren((LinkableWidget)w);
        result.append(renderer.processPage(renderer.getItemUIRegistry().getWidgetId(w),sitemapName,label,children,async));
      }
    }
  }
 catch (  RenderException e) {
    throw new ServletException(e.getMessage(),e);
  }
  if (async) {
    res.setContentType(CONTENT_TYPE_ASYNC);
  }
 else {
    res.setContentType(CONTENT_TYPE);
  }
  res.getWriter().append(result);
  res.getWriter().close();
}
