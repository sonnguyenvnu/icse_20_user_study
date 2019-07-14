/** 
 * Checks if source user can send dm to target user
 * @return true if source user can send dm to target user
 */
public boolean canSourceDm(){
  return getTarget().canSourceDm();
}
