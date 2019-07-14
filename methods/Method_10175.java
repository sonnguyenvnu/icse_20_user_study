/** 
 * Converts the specified markdown text to HTML.
 * @param markdownText the specified markdown text
 * @return converted HTML, returns an empty string "" if the specified markdown text is "" or {@code null}, returns 'markdownErrorLabel' if exception
 */
public static String toHTML(final String markdownText){
  if (StringUtils.isBlank(markdownText)) {
    return "";
  }
  final String cachedHTML=getHTML(markdownText);
  if (null != cachedHTML) {
    return cachedHTML;
  }
  final BeanManager beanManager=BeanManager.getInstance();
  final LangPropsService langPropsService=beanManager.getReference(LangPropsService.class);
  final UserQueryService userQueryService=beanManager.getReference(UserQueryService.class);
  final ExecutorService pool=Executors.newSingleThreadExecutor();
  final long[] threadId=new long[1];
  final Callable<String> call=() -> {
    threadId[0]=Thread.currentThread().getId();
    String html=langPropsService.get("contentRenderFailedLabel");
    if (MARKDOWN_HTTP_AVAILABLE) {
      try {
        html=toHtmlByMarkdownHTTP(markdownText);
      }
 catch (      final Exception e) {
        LOGGER.log(Level.WARN,"Failed to use [markdown-http] for markdown [md=" + StringUtils.substring(markdownText,0,256) + "]: " + e.getMessage());
        html=toHtmlByFlexmark(markdownText);
      }
    }
 else {
      html=toHtmlByFlexmark(markdownText);
    }
    if (!StringUtils.startsWith(html,"<p>")) {
      html="<p>" + html + "</p>";
    }
    final Whitelist whitelist=Whitelist.relaxed();
    inputWhitelist(whitelist);
    html=Jsoup.clean(html,whitelist);
    final Document doc=Jsoup.parse(html);
    final List<org.jsoup.nodes.Node> toRemove=new ArrayList<>();
    doc.traverse(new NodeVisitor(){
      @Override public void head(      final org.jsoup.nodes.Node node,      int depth){
        if (node instanceof org.jsoup.nodes.TextNode) {
          final org.jsoup.nodes.TextNode textNode=(org.jsoup.nodes.TextNode)node;
          final org.jsoup.nodes.Node parent=textNode.parent();
          if (parent instanceof Element) {
            final Element parentElem=(Element)parent;
            if (!parentElem.tagName().equals("code")) {
              String text=textNode.getWholeText();
              boolean nextIsBr=false;
              final org.jsoup.nodes.Node nextSibling=textNode.nextSibling();
              if (nextSibling instanceof Element) {
                nextIsBr="br".equalsIgnoreCase(((Element)nextSibling).tagName());
              }
              if (null != userQueryService) {
                final Set<String> userNames=userQueryService.getUserNames(text);
                for (                final String userName : userNames) {
                  text=text.replace('@' + userName + (nextIsBr ? "" : " "),"@" + UserExt.getUserLink(userName));
                }
                text=text.replace("@participants ","@<a href='" + Latkes.getServePath() + "/about' target='_blank' class='ft-red'>participants</a> ");
              }
              if (text.contains("@<a href=")) {
                final List<org.jsoup.nodes.Node> nodes=Parser.parseFragment(text,parentElem,"");
                final int index=textNode.siblingIndex();
                parentElem.insertChildren(index,nodes);
                toRemove.add(node);
              }
 else {
                textNode.text(Pangu.spacingText(text));
              }
            }
          }
        }
      }
      @Override public void tail(      org.jsoup.nodes.Node node,      int depth){
      }
    }
);
    toRemove.forEach(node -> node.remove());
    doc.select("pre>code").addClass("hljs");
    doc.select("a").forEach(a -> {
      String src=a.attr("href");
      if (StringUtils.containsIgnoreCase(src,"javascript:")) {
        a.remove();
        return;
      }
      if (StringUtils.startsWithAny(src,new String[]{Latkes.getServePath(),Symphonys.UPLOAD_QINIU_DOMAIN}) || StringUtils.endsWithIgnoreCase(src,".mov")) {
        return;
      }
      src=URLs.encode(src);
      a.attr("href",Latkes.getServePath() + "/forward?goto=" + src);
      a.attr("target","_blank");
      a.attr("rel","nofollow");
    }
);
    doc.outputSettings().prettyPrint(false);
    String ret=doc.select("body").html();
    ret=StringUtils.trim(ret);
    putHTML(markdownText,ret);
    return ret;
  }
;
  Stopwatchs.start("Md to HTML");
  try {
    final Future<String> future=pool.submit(call);
    return future.get(Symphonys.MARKDOWN_TIMEOUT,TimeUnit.MILLISECONDS);
  }
 catch (  final TimeoutException e) {
    LOGGER.log(Level.ERROR,"Markdown timeout [md=" + StringUtils.substring(markdownText,0,256) + "]");
    Callstacks.printCallstack(Level.ERROR,new String[]{"org.b3log"},null);
    final Set<Thread> threads=Thread.getAllStackTraces().keySet();
    for (    final Thread thread : threads) {
      if (thread.getId() == threadId[0]) {
        thread.stop();
        break;
      }
    }
  }
catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Markdown failed [md=" + StringUtils.substring(markdownText,0,256) + "]",e);
  }
 finally {
    pool.shutdownNow();
    Stopwatchs.end();
  }
  return langPropsService.get("contentRenderFailedLabel");
}
