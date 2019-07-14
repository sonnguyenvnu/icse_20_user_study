/** 
 * Show a renderer error, and keep track of it so that it's only shown once.
 * @param msg the error message (which will be stored for later comparison)
 */
static public void showWarning(String msg){
  if (warnings == null) {
    warnings=new HashMap<>();
  }
  if (!warnings.containsKey(msg)) {
    System.err.println(msg);
    warnings.put(msg,new Object());
  }
}
