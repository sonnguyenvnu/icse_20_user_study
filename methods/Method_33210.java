private void init(RecursiveTreeObject<T> value){
  addChildrenListener(value);
  valueProperty().addListener(observable -> {
    if (getValue() != null) {
      addChildrenListener(getValue());
    }
  }
);
  predicate.addListener(observable -> {
    filteredItems.setPredicate(child -> {
      if (child instanceof RecursiveTreeItem) {
        if (!((RecursiveTreeItem)child).originalItems.isEmpty()) {
          RecursiveTreeItem<T> filterableChild=(RecursiveTreeItem<T>)child;
          filterableChild.setPredicate(predicate.get());
        }
      }
      if (predicate.get() == null) {
        return true;
      }
      if (child.getChildren().size() > 0) {
        return true;
      }
      if (child.getValue() instanceof RecursiveTreeObject && child.getValue().getClass() == RecursiveTreeObject.class) {
        return child.getChildren().size() != 0;
      }
      return predicate.get().test(child);
    }
);
  }
);
  this.filteredItems.predicateProperty().addListener(observable -> JFXUtilities.runInFXAndWait(() -> {
    getChildren().clear();
    getChildren().setAll(filteredItems);
  }
));
}
