@Override public TableCell<S,T> call(TableColumn<S,T> param){
  return new TableCell<S,T>(){
    @Override protected void updateItem(    T item,    boolean empty){
      super.updateItem(item,empty);
      if (empty || (item == null) || (getTableRow() == null) || (getTableRow().getItem() == null)) {
        setText(null);
        setGraphic(null);
        setOnMouseClicked(null);
        setTooltip(null);
      }
 else {
        S rowItem=((TableRow<S>)getTableRow()).getItem();
        if (toText != null) {
          setText(toText.apply(item));
        }
        if (toGraphic != null) {
          setGraphic(toGraphic.apply(rowItem,item));
        }
        if (toTooltip != null) {
          String tooltipText=toTooltip.apply(rowItem,item);
          if (StringUtil.isNotBlank(tooltipText)) {
            setTooltip(new Tooltip(tooltipText));
          }
        }
        if (contextMenuFactory != null) {
          setOnContextMenuRequested(event -> {
            if (!isEmpty()) {
              setContextMenu(contextMenuFactory.apply(item));
              getContextMenu().show(this,event.getScreenX(),event.getScreenY());
            }
            event.consume();
          }
);
        }
        setOnMouseClicked(event -> {
          if (toOnMouseClickedEvent != null) {
            toOnMouseClickedEvent.apply(rowItem,item).handle(event);
          }
          if ((menuFactory != null) && !event.isConsumed()) {
            if (event.getButton() == MouseButton.PRIMARY) {
              ContextMenu menu=menuFactory.apply(rowItem,item);
              if (menu != null) {
                menu.show(this,event.getScreenX(),event.getScreenY());
                event.consume();
              }
            }
          }
        }
);
      }
    }
  }
;
}
