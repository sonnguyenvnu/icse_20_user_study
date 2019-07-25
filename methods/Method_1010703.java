private void select(@Nullable EditorContext context,String selectedCellId,SNode result){
  if (selectedCellId != null && context != null && result != null) {
    EditorCell toSelect=context.getEditorComponent().findCellWithId(result,selectedCellId);
    if (toSelect != null) {
      context.flushEvents();
      context.getSelectionManager().setSelection(toSelect);
      if (context.getSelectedCell() instanceof EditorCell_Label) {
        context.getSelectedCell().end();
      }
    }
  }
}
