@Override public TreeTableCell<S,T> call(TreeTableColumn<S,T> param){
  return new TreeTableCell<S,T>(){
    @Override protected void updateItem(    T item,    boolean empty){
      super.updateItem(item,empty);
      if (empty || getTreeTableRow() == null || getTreeTableRow().getItem() == null) {
        setText(null);
        setGraphic(null);
        setOnMouseClicked(null);
      }
 else {
        S viewModel=getTreeTableRow().getItem();
        if (toText != null) {
          setText(toText.call(viewModel));
        }
        if (toGraphic != null) {
          setGraphic(toGraphic.call(viewModel));
        }
        if (toTooltip != null) {
          String tooltip=toTooltip.call(viewModel);
          if (StringUtil.isNotBlank(tooltip)) {
            setTooltip(new Tooltip(tooltip));
          }
        }
        if (toOnMouseClickedEvent != null) {
          setOnMouseClicked(toOnMouseClickedEvent.call(viewModel));
        }
      }
    }
  }
;
}
