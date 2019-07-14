/** 
 * Well, we wouldn't want to copy when the target is inside the source otherwise it'll end into a loop
 * @return true when copy loop is possible
 */
public static boolean isCopyLoopPossible(HybridFileParcelable sourceFile,HybridFile targetFile){
  return targetFile.getPath().contains(sourceFile.getPath());
}
