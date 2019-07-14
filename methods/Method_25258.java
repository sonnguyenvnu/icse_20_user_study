private static void setNonnullIfTrackable(Updates updates,Node node){
  if (node instanceof LocalVariableNode) {
    updates.set((LocalVariableNode)node,NONNULL);
  }
 else   if (node instanceof FieldAccessNode) {
    updates.set((FieldAccessNode)node,NONNULL);
  }
 else   if (node instanceof VariableDeclarationNode) {
    updates.set((VariableDeclarationNode)node,NONNULL);
  }
}
