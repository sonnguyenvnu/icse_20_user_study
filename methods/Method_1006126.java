@Override public TreeCell<T> call(TreeView<T> tree){
  Callback<TreeItem<T>,ObservableValue<Boolean>> getSelectedProperty=item -> {
    if (item instanceof CheckBoxTreeItem<?>) {
      return ((CheckBoxTreeItem<?>)item).selectedProperty();
    }
    return null;
  }
;
  StringConverter<TreeItem<T>> converter=new StringConverter<TreeItem<T>>(){
    @Override public String toString(    TreeItem<T> treeItem){
      return (treeItem == null || treeItem.getValue() == null || toText == null) ? "" : toText.call(treeItem.getValue());
    }
    @Override public TreeItem<T> fromString(    String string){
      throw new UnsupportedOperationException("Not supported.");
    }
  }
;
  return new CheckBoxTreeCell<>(getSelectedProperty,converter);
}
