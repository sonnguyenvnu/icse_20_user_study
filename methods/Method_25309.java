/** 
 * Adds modifiers to the given declaration and corresponding modifiers tree. 
 */
public static Optional<SuggestedFix> removeModifiers(ModifiersTree originalModifiers,VisitorState state,Set<Modifier> toRemove){
  SuggestedFix.Builder fix=SuggestedFix.builder();
  List<ErrorProneToken> tokens=state.getOffsetTokensForNode(originalModifiers);
  boolean empty=true;
  for (  ErrorProneToken tok : tokens) {
    Modifier mod=getTokModifierKind(tok);
    if (toRemove.contains(mod)) {
      empty=false;
      fix.replace(tok.pos(),tok.endPos() + 1,"");
    }
  }
  if (empty) {
    return Optional.empty();
  }
  return Optional.of(fix.build());
}
