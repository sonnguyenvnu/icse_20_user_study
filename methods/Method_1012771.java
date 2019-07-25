/** 
 * Unregisters the listeners and releases the  {@link Window} set up by theconstructor. This instance will never be GC'ed unless this method is called first, since the registered listeners has references to this instance.
 */
public void dispose(){
  if (window != null) {
    window.removeWindowListener(this);
    window.removeComponentListener(this);
    window=null;
  }
}
