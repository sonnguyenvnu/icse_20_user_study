/** 
 * Replaces the leaf doctree in the given path with  {@code replacement}. 
 */
public static void replaceDocTree(SuggestedFix.Builder fix,DocTreePath docPath,String replacement){
  DocTree leaf=docPath.getLeaf();
  checkArgument(leaf instanceof DCTree.DCEndPosTree,"no end position information for %s",leaf.getKind());
  DCTree.DCEndPosTree<?> node=(DCTree.DCEndPosTree<?>)leaf;
  DCTree.DCDocComment comment=(DCTree.DCDocComment)docPath.getDocComment();
  fix.replace((int)node.getSourcePosition(comment),node.getEndPos(comment),replacement);
}
