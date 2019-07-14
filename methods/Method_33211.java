private void addChildrenListener(RecursiveTreeObject<T> value){
  final ObservableList<T> children=childrenFactory.call(value);
  originalItems=FXCollections.observableArrayList();
  itemsMap=new HashMap<>();
  for (  T child : children) {
    final RecursiveTreeItem<T> treeItem=new RecursiveTreeItem<>(child,getGraphic(),childrenFactory);
    originalItems.add(treeItem);
    itemsMap.put(child,treeItem);
  }
  filteredItems=new FilteredList<>(originalItems,(  TreeItem<T> t) -> true);
  this.getChildren().addAll(originalItems);
  children.addListener((ListChangeListener<T>)change -> {
    while (change.next()) {
      if (change.wasRemoved()) {
        List<TreeItem<T>> removedItems=new ArrayList<>();
        for (        T t : change.getRemoved()) {
          final TreeItem<T> treeItem=itemsMap.remove(t);
          if (treeItem != null) {
            removedItems.add(treeItem);
          }
        }
        if (originalItems.size() == removedItems.size()) {
          originalItems.clear();
          getChildren().clear();
        }
 else {
          getChildren().removeAll(removedItems);
          originalItems.removeAll(removedItems);
        }
      }
      if (change.wasAdded()) {
        List<RecursiveTreeItem<T>> addedItems=new ArrayList<>();
        for (        T newChild : change.getAddedSubList()) {
          final RecursiveTreeItem<T> newTreeItem=new RecursiveTreeItem<>(newChild,getGraphic(),childrenFactory);
          addedItems.add(newTreeItem);
          itemsMap.put(newChild,newTreeItem);
        }
        getChildren().addAll(addedItems);
        originalItems.addAll(addedItems);
      }
    }
  }
);
}
