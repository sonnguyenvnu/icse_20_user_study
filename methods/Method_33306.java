private double estimateHeight(){
  double borderWidth=snapVerticalInsets();
  JFXListView<T> listview=(JFXListView<T>)getSkinnable();
  double gap=listview.isExpanded() ? ((JFXListView<T>)getSkinnable()).getVerticalGap() * (getSkinnable().getItems().size()) : 0;
  double cellsHeight=0;
  for (int i=0; i < flow.getCellCount(); i++) {
    ListCell<T> cell=flow.getCell(i);
    cellsHeight+=cell.getHeight();
  }
  return cellsHeight + gap + borderWidth;
}
