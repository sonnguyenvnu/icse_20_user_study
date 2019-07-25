@Override public ListCell<T> call(ListView<T> param){
  return new ListCell<T>(){
    @Override protected void updateItem(    T item,    boolean empty){
      super.updateItem(item,empty);
      T viewModel=getItem();
      if (empty || (viewModel == null)) {
        setText(null);
        setGraphic(null);
        setOnMouseClicked(null);
        setTooltip(null);
      }
 else {
        if (toText != null) {
          setText(toText.call(viewModel));
        }
        if (toGraphic != null) {
          setGraphic(toGraphic.call(viewModel));
        }
        if (toOnMouseClickedEvent != null) {
          setOnMouseClicked(event -> toOnMouseClickedEvent.accept(viewModel,event));
        }
        if (toStyleClass != null) {
          getStyleClass().setAll(toStyleClass.call(viewModel));
        }
        if (toTooltip != null) {
          String tooltipText=toTooltip.call(viewModel);
          if (StringUtil.isNotBlank(tooltipText)) {
            setTooltip(new Tooltip(tooltipText));
          }
        }
        if (toContextMenu != null) {
          setContextMenu(toContextMenu.call(viewModel));
        }
        if (toOnDragDetected != null) {
          setOnDragDetected(event -> toOnDragDetected.accept(viewModel,event));
        }
        if (toOnDragDropped != null) {
          setOnDragDropped(event -> toOnDragDropped.accept(viewModel,event));
        }
        if (toOnDragEntered != null) {
          setOnDragEntered(event -> toOnDragEntered.accept(viewModel,event));
        }
        if (toOnDragExited != null) {
          setOnDragExited(event -> toOnDragExited.accept(viewModel,event));
        }
        if (toOnDragOver != null) {
          setOnDragOver(event -> toOnDragOver.accept(viewModel,event));
        }
        for (        Map.Entry<PseudoClass,Callback<T,ObservableValue<Boolean>>> pseudoClassWithCondition : pseudoClasses.entrySet()) {
          ObservableValue<Boolean> condition=pseudoClassWithCondition.getValue().call(viewModel);
          BindingsHelper.includePseudoClassWhen(this,pseudoClassWithCondition.getKey(),condition);
        }
      }
      getListView().refresh();
    }
  }
;
}
