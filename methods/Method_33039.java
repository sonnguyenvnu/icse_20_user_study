private boolean updateDisplayText(ListCell<T> cell,T item,boolean empty){
  if (empty) {
    if (cell == null) {
      return true;
    }
    cell.setGraphic(null);
    cell.setText(null);
    return true;
  }
 else   if (item instanceof Node) {
    Node currentNode=cell.getGraphic();
    Node newNode=(Node)item;
    NodeConverter<T> nc=this.getNodeConverter();
    Node node=nc == null ? null : nc.toNode(item);
    if (currentNode == null || !currentNode.equals(newNode)) {
      cell.setText(null);
      cell.setGraphic(node == null ? newNode : node);
    }
    return node == null;
  }
 else {
    StringConverter<T> c=this.getConverter();
    String s=item == null ? this.getPromptText() : (c == null ? item.toString() : c.toString(item));
    cell.setText(s);
    cell.setGraphic(null);
    return s == null || s.isEmpty();
  }
}
