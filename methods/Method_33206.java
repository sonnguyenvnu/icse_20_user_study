private void buildGroupedRoot(Map<?,?> groupedItems,RecursiveTreeItem parent,int groupIndex){
  boolean setRoot=false;
  if (parent == null) {
    parent=new RecursiveTreeItem<>(new RecursiveTreeObject(),RecursiveTreeObject::getChildren);
    setRoot=true;
  }
  for (  Map.Entry<?,?> entry : groupedItems.entrySet()) {
    Object key=entry.getKey();
    RecursiveTreeObject groupItem=new RecursiveTreeObject<>();
    groupItem.setGroupedValue(key);
    groupItem.setGroupedColumn(groupOrder.get(groupIndex));
    RecursiveTreeItem node=new RecursiveTreeItem<>(groupItem,RecursiveTreeObject::getChildren);
    node.expandedProperty().addListener((o,oldVal,newVal) -> {
      getSelectionModel().clearSelection();
    }
);
    parent.originalItems.add(node);
    parent.getChildren().add(node);
    Object children=entry.getValue();
    if (children instanceof List) {
      node.originalItems.addAll((List)children);
      node.getChildren().addAll((List)children);
    }
 else     if (children instanceof Map) {
      buildGroupedRoot((Map)children,node,groupIndex + 1);
    }
  }
  if (setRoot) {
    final RecursiveTreeItem<S> newParent=parent;
    JFXUtilities.runInFX(() -> {
      ArrayList<TreeTableColumn<S,?>> sortOrder=new ArrayList<>();
      sortOrder.addAll(getSortOrder());
      internalSetRoot=true;
      setRoot(newParent);
      internalSetRoot=false;
      getSortOrder().addAll(sortOrder);
      getSelectionModel().select(0);
    }
);
  }
}
