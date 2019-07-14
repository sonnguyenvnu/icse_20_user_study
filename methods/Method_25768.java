/** 
 * Returns true if the tree references its enclosing class. 
 */
public static boolean referencesOuter(Tree tree,Symbol owner,VisitorState state){
  CanBeStaticAnalyzer scanner=new CanBeStaticAnalyzer(owner,state);
  ((JCTree)tree).accept(scanner);
  return !scanner.canPossiblyBeStatic || !scanner.outerReferences.isEmpty();
}
