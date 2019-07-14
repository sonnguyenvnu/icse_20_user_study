/** 
 * Refines the receiver of a field access to type non-null after a successful field access, and refines the value of the expression as a whole to non-null if applicable (e.g., if the field has a primitive type or the  {@code store}) has a non-null value for this access path. <p>Note: If the field access occurs when the node is an l-value, the analysis won't call this method. Instead, it will call  {@link #visitAssignment}.
 */
@Override Nullness visitFieldAccess(FieldAccessNode node,Updates updates,AccessPathValues<Nullness> store){
  if (!node.isStatic()) {
    setNonnullIfTrackable(updates,node.getReceiver());
  }
  ClassAndField accessed=tryGetFieldSymbol(node.getTree());
  return fieldNullness(accessed,AccessPath.fromFieldAccess(node),store);
}
