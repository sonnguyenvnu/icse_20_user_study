public static void execute(EditorContext context){
  SelectionManager selectionManager=context.getEditorComponent().getSelectionManager();
  EditorCell cell=findTarget(selectionManager);
  selectionManager.pushSelection(selectionManager.createSelection(cell));
  if (cell instanceof EditorCell_Label) {
    ((EditorCell_Label)cell).selectWordOrAll();
  }
}
