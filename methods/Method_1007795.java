/** 
 * Reset the current request and clear all fields.
 */
public static void reset(){
  request().invalidate();
  threadLocal.remove();
}
