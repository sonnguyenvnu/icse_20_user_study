/** 
 * checks the children and creates them if neccessary
 */
private void checkChildren(){
  if (children == null) {
    children=new ArrayList<>(node.jjtGetNumChildren());
    for (int i=0; i < node.jjtGetNumChildren(); i++) {
      children.add(new SimpleNodeTreeNodeAdapter(this,node.jjtGetChild(i)));
    }
  }
}
