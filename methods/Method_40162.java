/** 
 * Add additional highlighting styles based on information not evident from the AST.
 */
private void addSemanticStyles(@NotNull Binding nb){
  boolean isConst=CONSTANT.matcher(nb.name).matches();
switch (nb.kind) {
case SCOPE:
    if (isConst) {
      addSemanticStyle(nb,Style.Type.CONSTANT);
    }
  break;
case VARIABLE:
addSemanticStyle(nb,isConst ? Style.Type.CONSTANT : Style.Type.IDENTIFIER);
break;
case PARAMETER:
addSemanticStyle(nb,Style.Type.PARAMETER);
break;
case CLASS:
addSemanticStyle(nb,Style.Type.TYPE_NAME);
break;
}
}
