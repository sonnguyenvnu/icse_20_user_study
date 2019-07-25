/** 
 * Disable native dialog callbacks, with user data.
 * @param userData opaque user data associated with each dialog
 */
public void disable(Pointer userData){
  libvlc_dialog_set_callbacks(libvlcInstance,null,userData);
}
