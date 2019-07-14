/** 
 * Returns the current disclosure node set in this cell.
 */
public final Node getDisclosureNode(){
  if (disclosureNode.get() == null) {
    final StackPane disclosureNode=new StackPane();
    disclosureNode.getStyleClass().setAll("tree-disclosure-node");
    disclosureNode.setMouseTransparent(true);
    final StackPane disclosureNodeArrow=new StackPane();
    disclosureNodeArrow.getStyleClass().setAll("arrow");
    disclosureNode.getChildren().add(disclosureNodeArrow);
    setDisclosureNode(disclosureNode);
  }
  return disclosureNode.get();
}
