/** 
 * Initializes list of child elements.
 */
protected void initChildElementNodes(){
  if (childElementNodes == null) {
    childElementNodes=new Element[childElementNodesCount];
    int childCount=getChildNodesCount();
    for (int i=0; i < childCount; i++) {
      Node child=getChild(i);
      if (child.siblingElementIndex >= 0) {
        childElementNodes[child.siblingElementIndex]=(Element)child;
      }
    }
  }
}
