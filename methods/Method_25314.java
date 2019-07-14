/** 
 * Fully qualifies a javadoc reference, e.g. for replacing  {@code} {@link List}} with  {@code} {@link java.util.List}}
 * @param fix the fix builder to add to
 * @param docPath the path to a {@link DCTree.DCReference} element
 */
public static void qualifyDocReference(SuggestedFix.Builder fix,DocTreePath docPath,VisitorState state){
  DocTree leaf=docPath.getLeaf();
  checkArgument(leaf.getKind() == DocTree.Kind.REFERENCE,"expected a path to a reference, got %s instead",leaf.getKind());
  DCTree.DCReference reference=(DCTree.DCReference)leaf;
  Symbol sym=(Symbol)JavacTrees.instance(state.context).getElement(docPath);
  if (sym == null) {
    return;
  }
  String refString=reference.toString();
  String qualifiedName;
  int idx=refString.indexOf('#');
  if (idx >= 0) {
    qualifiedName=sym.owner.getQualifiedName() + refString.substring(idx,refString.length());
  }
 else {
    qualifiedName=sym.getQualifiedName().toString();
  }
  replaceDocTree(fix,docPath,qualifiedName);
}
