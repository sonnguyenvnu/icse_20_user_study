/** 
 * Adds modifiers to the given declaration and corresponding modifiers tree. 
 */
public static Optional<SuggestedFix> addModifiers(Tree tree,ModifiersTree originalModifiers,VisitorState state,Set<Modifier> modifiers){
  Set<Modifier> toAdd=Sets.difference(modifiers,originalModifiers.getFlags());
  SuggestedFix.Builder fix=SuggestedFix.builder();
  List<Modifier> modifiersToWrite=new ArrayList<>();
  if (!originalModifiers.getFlags().isEmpty()) {
    Map<Modifier,Integer> modifierPositions=new TreeMap<>();
    for (    Modifier mod : toAdd) {
      modifierPositions.put(mod,-1);
    }
    List<ErrorProneToken> tokens=state.getOffsetTokensForNode(originalModifiers);
    for (    ErrorProneToken tok : tokens) {
      Modifier mod=getTokModifierKind(tok);
      if (mod != null) {
        modifierPositions.put(mod,tok.pos());
      }
    }
    for (    Modifier mod : modifierPositions.keySet()) {
      int p=modifierPositions.get(mod);
      if (p == -1) {
        modifiersToWrite.add(mod);
      }
 else       if (!modifiersToWrite.isEmpty()) {
        fix.replace(p,p,Joiner.on(' ').join(modifiersToWrite) + " ");
        modifiersToWrite.clear();
      }
    }
  }
 else {
    modifiersToWrite.addAll(toAdd);
  }
  addRemainingModifiers(tree,state,originalModifiers,modifiersToWrite,fix);
  return Optional.of(fix.build());
}
