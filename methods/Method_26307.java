/** 
 * Report a  {@link Description} if a field used in double-checked locking is not volatile.<p>If the AST node for the field declaration can be located in the current compilation unit, suggest adding the volatile modifier.
 */
private Description handleField(IfTree outerIf,VarSymbol sym,VisitorState state){
  if (sym.getModifiers().contains(Modifier.VOLATILE)) {
    return Description.NO_MATCH;
  }
  if (isImmutable(sym.type,state)) {
    return Description.NO_MATCH;
  }
  Description.Builder builder=buildDescription(outerIf);
  JCTree fieldDecl=findFieldDeclaration(state.getPath(),sym);
  if (fieldDecl != null) {
    builder.addFix(SuggestedFixes.addModifiers(fieldDecl,state,Modifier.VOLATILE));
  }
  return builder.build();
}
