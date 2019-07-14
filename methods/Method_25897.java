/** 
 * Get the outermost class/interface/enum of an element, or null if none. 
 */
private Type getOutermostClass(VisitorState state){
  TreePath path=state.getPath();
  Type type=null;
  while (path != null) {
    if (path.getLeaf().getKind() == Kind.CLASS || path.getLeaf().getKind() == Kind.INTERFACE || path.getLeaf().getKind() == Kind.ENUM) {
      type=ASTHelpers.getSymbol(path.getLeaf()).type;
    }
    path=path.getParentPath();
  }
  return type;
}
