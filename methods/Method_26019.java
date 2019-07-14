/** 
 * Gets a  {@link DiagnosticPosition} for the {@link DocTree} pointed to by {@code path}, attached to the  {@link Tree} which it documents.
 */
static DiagnosticPosition diagnosticPosition(DocTreePath path,VisitorState state){
  int startPosition=getStartPosition(path.getLeaf(),state);
  Tree tree=path.getTreePath().getLeaf();
  return getDiagnosticPosition(startPosition,tree);
}
