/** 
 * This controls the default icon and disclosure triangle.
 * @return true, will show "folder" icon and disclosure triangle.
 */
@Override public boolean isLeaf(){
  return !getAllowsChildren();
}
