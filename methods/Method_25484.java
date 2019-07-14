/** 
 * Scan a tree from a position identified by a TreePath. 
 */
@Override public Void scan(TreePath path,VisitorState state){
  SuppressionInfo prevSuppressionInfo=updateSuppressions(path.getLeaf(),state);
  try {
    return super.scan(path,state);
  }
  finally {
    currentSuppressions=prevSuppressionInfo;
  }
}
