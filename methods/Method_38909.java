/** 
 * Filter nodes.
 */
protected boolean filter(final List<Node> currentResults,final Node node,final CssSelector cssSelector,final int index){
  return cssSelector.accept(currentResults,node,index);
}
