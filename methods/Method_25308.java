/** 
 * Remove modifiers from the given class, method, or field declaration. 
 */
public static Optional<SuggestedFix> removeModifiers(Tree tree,VisitorState state,Modifier... modifiers){
  Set<Modifier> toRemove=ImmutableSet.copyOf(modifiers);
  ModifiersTree originalModifiers=getModifiers(tree);
  if (originalModifiers == null) {
    return Optional.empty();
  }
  return removeModifiers(originalModifiers,state,toRemove);
}
