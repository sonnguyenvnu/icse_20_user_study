/** 
 * Matches an AST node whose compilation unit's package name matches the given pattern. 
 */
public static <T extends Tree>Matcher<T> packageMatches(Pattern pattern){
  return (tree,state) -> pattern.matcher(getPackageFullName(state)).matches();
}
