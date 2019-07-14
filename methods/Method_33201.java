protected void init(){
  this.getStyleClass().add(DEFAULT_STYLE_CLASS);
  this.setRowFactory(param -> new JFXTreeTableRow<>());
  this.getSelectionModel().selectedItemProperty().addListener((o,oldVal,newVal) -> {
    if (newVal != null && newVal.getValue() != null) {
      itemWasSelected=true;
    }
  }
);
  this.predicate.addListener(observable -> filter(getPredicate()));
  this.sceneProperty().addListener(observable -> {
    if (getScene() == null) {
      threadPool.shutdownNow();
    }
 else     if (threadPool.isTerminated()) {
      threadPool=createThreadPool();
    }
  }
);
  this.rootProperty().addListener(observable -> {
    if (getRoot() != null) {
      setCurrentItemsCount(count(getRoot()));
    }
    if (!internalSetRoot) {
      originalRoot=getRoot();
      reGroup();
    }
  }
);
  setCurrentItemsCount(count(getRoot()));
}
