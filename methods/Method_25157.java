/** 
 * Returns an AccessPath representing  {@code node} if {@code node} is representable as an accesspath and null otherwise
 */
@Nullable public static AccessPath fromNodeIfTrackable(Node node){
  if (node instanceof LocalVariableNode) {
    return fromLocalVariable((LocalVariableNode)node);
  }
 else   if (node instanceof VariableDeclarationNode) {
    return fromVariableDecl((VariableDeclarationNode)node);
  }
 else   if (node instanceof FieldAccessNode) {
    return fromFieldAccess((FieldAccessNode)node);
  }
 else   if (node instanceof AssignmentNode) {
    return fromNodeIfTrackable(((AssignmentNode)node).getTarget());
  }
  return null;
}
