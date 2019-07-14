/** 
 * Registers/unregisters for participation in populating the options menu by receiving options-related callbacks, such as  {@link #onCreateOptionsMenu(Menu,MenuInflater)}
 * @param hasOptionsMenu If true, this controller's options menu callbacks will be called.
 */
public final void setHasOptionsMenu(boolean hasOptionsMenu){
  boolean invalidate=attached && !optionsMenuHidden && this.hasOptionsMenu != hasOptionsMenu;
  this.hasOptionsMenu=hasOptionsMenu;
  if (invalidate) {
    router.invalidateOptionsMenu();
  }
}
