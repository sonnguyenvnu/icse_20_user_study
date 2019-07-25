@Override public void activate(){
  String initValue="";
  if (myEditor.getDeepestSelectedCell() instanceof EditorCell_Label) {
    EditorCell_Label cell_label=(EditorCell_Label)myEditor.getDeepestSelectedCell();
    if (cell_label.getSelectionStart() != cell_label.getSelectionEnd()) {
      initValue=TextRenderUtil.getTextBuilderForSelectedCellsOfEditor(myEditor).getText();
    }
  }
  setInitialText(initValue);
  myEditor.addUpperComponent(this);
  super.activate();
}
