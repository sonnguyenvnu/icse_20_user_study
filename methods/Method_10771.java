/** 
 * ????
 * @param srcDir  ???
 * @param destDir ????
 * @return {@code true}: ????<br> {@code false}: ????
 */
public static boolean moveDir(File srcDir,File destDir){
  return copyOrMoveDir(srcDir,destDir,true);
}
