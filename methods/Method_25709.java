/** 
 * Return true if this parameter does not match any of the regular expressions in the list of overloaded words.
 */
@Override public boolean isAcceptableChange(Changes changes,Tree node,MethodSymbol symbol,VisitorState state){
  return changes.changedPairs().stream().allMatch(p -> findMatch(p.formal()) == null);
}
