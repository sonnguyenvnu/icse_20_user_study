/** 
 * Add additional highlighting styles based on information not evident from the AST.
 */
private void addSemanticStyles(Binding nb){
  Def def=nb.getSignatureNode();
  if (def == null || !def.isName()) {
    return;
  }
  boolean isConst=CONSTANT.matcher(def.getName()).matches();
switch (nb.getKind()) {
case SCOPE:
    if (isConst) {
      addSemanticStyle(def,StyleRun.Type.CONSTANT);
    }
  break;
case VARIABLE:
addSemanticStyle(def,isConst ? StyleRun.Type.CONSTANT : StyleRun.Type.IDENTIFIER);
break;
case PARAMETER:
addSemanticStyle(def,StyleRun.Type.PARAMETER);
break;
case CLASS:
addSemanticStyle(def,StyleRun.Type.TYPE_NAME);
break;
}
}
