private void updateDisplay(T item,boolean empty){
  if (item == null || empty) {
    hbox=null;
    setText(null);
    setGraphic(null);
  }
 else {
    TreeItem<T> treeItem=getTreeItem();
    if (treeItem != null && treeItem.getGraphic() != null) {
      if (item instanceof Node) {
        setText(null);
        if (hbox == null) {
          hbox=new HBox(3);
        }
        hbox.getChildren().setAll(treeItem.getGraphic(),(Node)item);
        setGraphic(hbox);
      }
 else {
        hbox=null;
        setText(item.toString());
        setGraphic(treeItem.getGraphic());
      }
    }
 else {
      hbox=null;
      if (item instanceof Node) {
        setText(null);
        setGraphic((Node)item);
      }
 else {
        setText(item.toString());
        setGraphic(null);
      }
    }
  }
}
