/** 
 * @param level
 * @return
 */
public static boolean isContainLevel(int level){
  for (  int existLevel : LEVELS) {
    if (level == existLevel) {
      return true;
    }
  }
  return false;
}
