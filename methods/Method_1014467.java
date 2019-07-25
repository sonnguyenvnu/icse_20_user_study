@Override public void service(ServletRequest req,ServletResponse res) throws ServletException, IOException {
  logger.debug("Servlet request received!");
  String sitemapName=req.getParameter("sitemap");
  String widgetId=req.getParameter("w");
  boolean async="true".equalsIgnoreCase(req.getParameter("__async"));
  boolean poll="true".equalsIgnoreCase(req.getParameter("poll"));
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
      throw new RenderException("Sitemap '" + sitemapName + "' could not be found");
    }
    logger.debug("reading sitemap {} widgetId {} async {} poll {}",sitemap.getName(),widgetId,async,poll);
    if (widgetId == null || widgetId.isEmpty() || widgetId.equals("Home")) {
      String label=sitemap.getLabel() != null ? sitemap.getLabel() : sitemapName;
      EList<Widget> children=renderer.getItemUIRegistry().getChildren(sitemap);
      if (poll && !waitForChanges(children)) {
        res.getWriter().append(getTimeoutResponse()).close();
        return;
      }
      result.append(renderer.processPage("Home",sitemapName,label,children,async));
    }
 else     if (!widgetId.equals("Colorpicker")) {
      Widget w=renderer.getItemUIRegistry().getWidget(sitemap,widgetId);
      if (w != null) {
        if (!(w instanceof LinkableWidget)) {
          throw new RenderException("Widget '" + w + "' can not have any content");
        }
        LinkableWidget lw=(LinkableWidget)w;
        EList<Widget> children=renderer.getItemUIRegistry().getChildren(lw);
        EList<Widget> parentAndChildren=new BasicEList<Widget>();
        parentAndChildren.add(lw);
        parentAndChildren.addAll(children);
        if (poll && !waitForChanges(parentAndChildren)) {
          res.getWriter().append(getTimeoutResponse()).close();
          return;
        }
        String label=renderer.getItemUIRegistry().getLabel(w);
        if (label == null) {
          label="undefined";
        }
        result.append(renderer.processPage(renderer.getItemUIRegistry().getWidgetId(w),sitemapName,label,children,async));
      }
    }
  }
 catch (  RenderException e) {
    throw new ServletException(e.getMessage(),e);
  }
  if (async) {
    res.setContentType("application/xml;charset=UTF-8");
  }
 else {
    res.setContentType("text/html;charset=UTF-8");
  }
  res.getWriter().append(result);
  res.getWriter().close();
}
