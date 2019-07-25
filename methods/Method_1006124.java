@Override public TextFieldTableCell<S,T> call(TableColumn<S,T> param){
  return new TextFieldTableCell<S,T>(stringConverter){
    @Override public void updateItem(    T item,    boolean empty){
      super.updateItem(item,empty);
      if (!empty && (getTableRow() != null)) {
        Object rowItem=getTableRow().getItem();
        if (rowItem != null) {
          S vm=(S)rowItem;
          if ((visualizer != null) && (validationStatusProperty != null)) {
            visualizer.initVisualization(validationStatusProperty.apply(vm),this);
          }
        }
      }
    }
  }
;
}
