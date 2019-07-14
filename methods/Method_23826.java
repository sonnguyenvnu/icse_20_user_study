/** 
 * Honey, can you just check on the kids? Thanks. Internal function; not included in reference.
 */
protected void checkChildren(){
  if (children == null) {
    NodeList kids=node.getChildNodes();
    int childCount=kids.getLength();
    children=new XML[childCount];
    for (int i=0; i < childCount; i++) {
      children[i]=new XML(this,kids.item(i));
    }
  }
}
