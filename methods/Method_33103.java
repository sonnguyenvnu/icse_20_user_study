private void initialize(){
  this.getStyleClass().add(DEFAULT_STYLE_CLASS);
  expanded.addListener((o,oldVal,newVal) -> {
    if (newVal) {
      expand();
    }
 else {
      collapse();
    }
  }
);
  verticalGap.addListener((o,oldVal,newVal) -> {
    if (isExpanded()) {
      expand();
    }
 else {
      collapse();
    }
  }
);
  sublistsProperty.get().addListener((  ListChangeListener.Change<? extends JFXListView<?>> c) -> {
    while (c.next()) {
      if (c.wasAdded() || c.wasUpdated() || c.wasReplaced()) {
        if (sublistsProperty.get().size() == 1) {
          this.getSelectionModel().selectedItemProperty().addListener((o,oldVal,newVal) -> clearSelection(this));
          this.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED,Event::consume);
        }
        c.getAddedSubList().forEach(item -> item.getSelectionModel().selectedItemProperty().addListener((o,oldVal,newVal) -> clearSelection(item)));
      }
    }
  }
);
  this.getSelectionModel().selectedIndexProperty().addListener((o,oldVal,newVal) -> {
    if (newVal.intValue() != -1) {
      updateOverAllSelectedIndex();
    }
  }
);
}
