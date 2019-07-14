/** 
 * Matches an AST node of a given kind, for example, an Annotation or a switch block. 
 */
public static <T extends Tree>Matcher<T> kindAnyOf(Set<Kind> kinds){
  return (tree,state) -> kinds.contains(tree.getKind());
}
