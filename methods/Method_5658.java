/** 
 * Populates a  {@link Window} with data for the window at the specified index.
 * @param windowIndex The index of the window.
 * @param window The {@link Window} to populate. Must not be null.
 * @param setTag Whether {@link Window#tag} should be populated. If false, the field will be setto null. The caller should pass false for efficiency reasons unless the field is required.
 * @return The populated {@link Window}, for convenience.
 */
public final Window getWindow(int windowIndex,Window window,boolean setTag){
  return getWindow(windowIndex,window,setTag,0);
}
