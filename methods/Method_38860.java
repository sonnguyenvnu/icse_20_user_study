protected NodeVisitor createRenderer(final Appendable appendable){
  return new LagartoHtmlRendererNodeVisitor(appendable);
}
