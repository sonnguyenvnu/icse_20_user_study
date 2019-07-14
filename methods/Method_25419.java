/** 
 * Matches an AST node whose compilation unit starts with this prefix. 
 */
public static <T extends Tree>Matcher<T> packageStartsWith(String prefix){
  return (tree,state) -> getPackageFullName(state).startsWith(prefix);
}
