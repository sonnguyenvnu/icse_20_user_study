public static boolean approve(@Nullable EditorContext context,@Nullable SNode node,@Nullable String cellId){
  if (context == null || node == null) {
    return false;
  }
  EditorCell nodeCell=getNodeCell(context,node,cellId);
  if (nodeCell == null) {
    return false;
  }
  if (!context.getEditorComponent().getDeletionApprover().isApprovedForDeletion(nodeCell) && !context.getSelectionManager().getSelection().isExactlyCoveringCell(nodeCell)) {
    context.getEditorComponent().getDeletionApprover().approveForDeletion(nodeCell);
    return true;
  }
  return false;
}
