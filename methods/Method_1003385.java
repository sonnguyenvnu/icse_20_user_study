/** 
 * Emit a beep.
 */
protected void beep(){
  sysOut.print("\007");
  sysOut.flush();
}
