/** 
 * True if the specified extension should be hidden when shown on a tab. For Processing, this is true for .pde files. (Broken out for subclasses.) You can override this in your Mode subclass to handle it differently.
 */
public boolean hideExtension(String what){
  return what.equals(getDefaultExtension());
}
