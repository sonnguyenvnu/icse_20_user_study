/** 
 * Accepts single node.
 */
@Override public boolean accept(final Node node){
  if (!matchElement(node)) {
    return false;
  }
  int totalSelectors=selectorsCount();
  for (int i=0; i < totalSelectors; i++) {
    Selector selector=getSelector(i);
switch (selector.getType()) {
case ATTRIBUTE:
      if (!((AttributeSelector)selector).accept(node)) {
        return false;
      }
    break;
case PSEUDO_CLASS:
  if (!((PseudoClassSelector)selector).accept(node)) {
    return false;
  }
break;
case PSEUDO_FUNCTION:
if (!((PseudoFunctionSelector)selector).accept(node)) {
return false;
}
break;
}
}
return true;
}
