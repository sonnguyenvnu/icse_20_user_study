private void updateListHeight(){
  final double height=Math.min(suggestionList.getItems().size(),getSkinnable().getCellLimit()) * suggestionList.getFixedCellSize();
  suggestionList.setPrefHeight(height + suggestionList.getFixedCellSize() / 2);
}
