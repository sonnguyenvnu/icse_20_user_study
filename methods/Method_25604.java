/** 
 * Returns the  {@link TreePath} to the nearest tree node of one of the given types. To insteadretrieve the element directly, use  {@link #findEnclosing(Class)}.
 * @return the path, or {@code null} if there is no match
 */
@Nullable @SafeVarargs public final TreePath findPathToEnclosing(Class<? extends Tree>... classes){
  TreePath enclosingPath=getPath();
  while (enclosingPath != null) {
    for (    Class<? extends Tree> clazz : classes) {
      if (clazz.isInstance(enclosingPath.getLeaf())) {
        return enclosingPath;
      }
    }
    enclosingPath=enclosingPath.getParentPath();
  }
  return null;
}
