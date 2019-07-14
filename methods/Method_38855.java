/** 
 * Fixes all unclosed tags up to matching parent. Missing end tags will be added just before parent tag is closed, making the whole inner content as its tag body. <p> Tags that can be closed implicitly are checked and closed. <p> There is optional check for detecting orphan tags inside the table or lists. If set, tags can be closed beyond the border of the table and the list and it is reported as orphan tag. <p> This is just a generic solutions, closest to the rules.
 */
protected void fixUnclosedTagsUpToMatchingParent(final Tag tag,final Node matchingParent){
  if (domBuilder.config.isUnclosedTagAsOrphanCheck()) {
    Node thisNode=parentNode;
    if (!CharSequenceUtil.equalsIgnoreCase(tag.getName(),"table")) {
      while (thisNode != matchingParent) {
        String thisNodeName=thisNode.getNodeName().toLowerCase();
        if (thisNodeName.equals("table") || thisNodeName.equals("ul") || thisNodeName.equals("ol")) {
          String positionString=tag.getPosition();
          if (positionString == null) {
            positionString=StringPool.EMPTY;
          }
          error("Orphan closed tag ignored: </" + tag.getName() + "> " + positionString);
          return;
        }
        thisNode=thisNode.getParentNode();
      }
    }
  }
  while (true) {
    if (parentNode == matchingParent) {
      parentNode=parentNode.getParentNode();
      break;
    }
    Node parentParentNode=parentNode.getParentNode();
    if (domBuilder.config.isImpliedEndTags()) {
      if (implRules.implicitlyCloseParentTagOnNewTag(parentParentNode.getNodeName(),parentNode.getNodeName())) {
        parentNode.detachFromParent();
        parentParentNode.getParentNode().addChild(parentNode);
      }
    }
    error("Unclosed tag closed: <" + parentNode.getNodeName() + ">");
    parentNode=parentParentNode;
  }
}
