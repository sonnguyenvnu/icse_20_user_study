/** 
 * Enable native dialog callbacks, with user data.
 * @param dialogs dialogs callback component
 * @param userData opaque user data associated with each dialog
 */
public void enable(Dialogs dialogs,Pointer userData){
  libvlc_dialog_set_callbacks(libvlcInstance,dialogs.callbacks(),userData);
}
