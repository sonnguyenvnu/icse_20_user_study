public void execute(EditorContext editorContext,boolean saveSelection){
  if (saveSelection) {
    EditorCell selectedCell=editorContext.getSelectedCell();
    if (selectedCell == null) {
      return;
    }
    int caretX=selectedCell.getCaretX();
    int caretY=selectedCell.getBaseline();
    execute(editorContext);
    editorContext.flushEvents();
    EditorCell rootCell=editorContext.getEditorComponent().getRootCell();
    EditorCell leaf=rootCell.findLeaf(caretX,caretY);
    if (leaf != null && leaf.getX() == caretX && !StyleAttributesUtil.isFirstPositionAllowed(leaf.getStyle())) {
      while (leaf != null && leaf.getPrevSibling() == null) {
        leaf=leaf.getParent();
      }
      if (leaf != null) {
        leaf=CellFinderUtil.findLastSelectableLeaf(leaf.getPrevSibling(),true);
      }
    }
    if (leaf != null) {
      editorContext.getEditorComponent().changeSelection(leaf);
      leaf.setCaretX(caretX);
    }
  }
 else {
    execute(editorContext);
  }
}
