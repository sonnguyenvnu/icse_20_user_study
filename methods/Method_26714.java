/** 
 * Gets the binding of the underlying identifier in the unifier. 
 */
public JCExpression getUnderlyingBinding(Unifier unifier){
  return (unifier == null) ? null : unifier.getBinding(new UFreeIdent.Key(identifier()));
}
