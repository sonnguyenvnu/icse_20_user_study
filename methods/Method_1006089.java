@Override public Boolean call(TableView.ResizeFeatures prop){
  if (prop.getColumn() == null) {
    return initColumnSize(prop.getTable());
  }
 else {
    return constrainedResize(prop);
  }
}
