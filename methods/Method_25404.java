/** 
 * Returns true if the Tree node has the expected  {@code Modifier}. 
 */
public static <T extends Tree>Matcher<T> hasModifier(Modifier modifier){
  return (tree,state) -> {
    Symbol sym=getSymbol(tree);
    return sym != null && sym.getModifiers().contains(modifier);
  }
;
}
