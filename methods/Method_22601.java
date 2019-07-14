public DefaultMutableTreeNode buildSketchbookTree(){
  DefaultMutableTreeNode sbNode=new DefaultMutableTreeNode(Language.text("sketchbook.tree"));
  try {
    base.addSketches(sbNode,Base.getSketchbookFolder(),false);
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return sbNode;
}
