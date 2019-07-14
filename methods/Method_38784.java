/** 
 * Accepts node within current results.
 */
public boolean accept(final List<Node> currentResults,final Node node,final int index){
  int totalSelectors=selectorsCount();
  for (int i=0; i < totalSelectors; i++) {
    Selector selector=getSelector(i);
switch (selector.getType()) {
case PSEUDO_FUNCTION:
      if (!((PseudoFunctionSelector)selector).accept(currentResults,node,index)) {
        return false;
      }
    break;
case PSEUDO_CLASS:
  if (!((PseudoClassSelector)selector).accept(currentResults,node,index)) {
    return false;
  }
break;
default :
}
}
return true;
}
