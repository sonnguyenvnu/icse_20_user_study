@Override protected LagartoParsingProcessor createParsingProcessor(){
  if (!enabled) {
    return null;
  }
  return new LagartoParsingProcessor(){
    @Override protected char[] parse(    final TagWriter rootTagWriter,    final HttpServletRequest request){
      TagVisitor visitor=rootTagWriter;
      if (stripHtml) {
        visitor=new StripHtmlTagAdapter(rootTagWriter){
          @Override public void end(){
            super.end();
            if (log.isDebugEnabled()) {
              log.debug("Stripped: " + getStrippedCharsCount() + " chars");
            }
          }
        }
;
      }
      String servletPath=DispatcherUtil.getServletPath(request);
      HtmlStaplerTagAdapter htmlStaplerTagAdapter=new HtmlStaplerTagAdapter(bundlesManager,servletPath,visitor);
      char[] content=invokeLagarto(htmlStaplerTagAdapter);
      return htmlStaplerTagAdapter.postProcess(content);
    }
  }
;
}
