/** 
 * Adds modifiers to the given class, method, or field declaration. 
 */
public static Optional<SuggestedFix> addModifiers(Tree tree,VisitorState state,Modifier... modifiers){
  ModifiersTree originalModifiers=getModifiers(tree);
  if (originalModifiers == null) {
    return Optional.empty();
  }
  return addModifiers(tree,originalModifiers,state,new TreeSet<>(Arrays.asList(modifiers)));
}
