/** 
 * Returns the outermost enclosing owning class, or  {@code null}. Doesn't crash on symbols that aren't containing in a package, unlike  {@link Symbol#outermostClass} (see b/123431414).
 */
public static ClassSymbol outermostClass(Symbol symbol){
  ClassSymbol curr=symbol.enclClass();
  while (curr.owner != null) {
    ClassSymbol encl=curr.owner.enclClass();
    if (encl == null) {
      break;
    }
    curr=encl;
  }
  return curr;
}
