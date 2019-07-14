/** 
 * Re-build a  {@link TreePath} from a previous path using equals-checksstarting at the root node. This is used to use paths from previous trees for use on the current tree.
 * @param path the path to synthesize.
 * @return the rebuilt path, usable on the current tree.
 */
protected TreePath synthesizePath(TreePath path){
  if (path.getPathCount() == 0 || !rootNode.equals(path.getPathComponent(0))) {
    return null;
  }
  Object[] newPath=new Object[path.getPathCount()];
  newPath[0]=rootNode;
  TreeNode currentNode=rootNode;
  for (int i=0; i < path.getPathCount() - 1; i++) {
    for (int j=0; j < currentNode.getChildCount(); j++) {
      TreeNode nextNode=currentNode.getChildAt(j);
      if (nextNode.equals(path.getPathComponent(i + 1))) {
        currentNode=nextNode;
        newPath[i + 1]=nextNode;
        break;
      }
    }
    if (newPath[i + 1] == null) {
      return null;
    }
  }
  return new TreePath(newPath);
}
