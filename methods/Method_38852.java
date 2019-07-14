/** 
 * Creates new element with correct configuration.
 */
protected Element createElementNode(final Tag tag){
  boolean hasVoidTags=htmlVoidRules != null;
  boolean isVoid=false;
  boolean selfClosed=false;
  if (hasVoidTags) {
    isVoid=htmlVoidRules.isVoidTag(tag.getName());
    if (isVoid) {
      selfClosed=domBuilder.config.isSelfCloseVoidTags();
    }
  }
 else {
    selfClosed=domBuilder.config.isSelfCloseVoidTags();
  }
  return new Element(rootNode,tag,isVoid,selfClosed);
}
