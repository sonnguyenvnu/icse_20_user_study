/** 
 * Installs the on focus change listeners that will dispatch the click handler defined in the component's props. Unconditionally set the clickable flag on the view.
 */
private static void setFocusChangeHandler(EventHandler<FocusChangedEvent> focusChangeHandler,View view){
  if (focusChangeHandler == null) {
    return;
  }
  ComponentFocusChangeListener listener=getComponentFocusChangeListener(view);
  if (listener == null) {
    listener=new ComponentFocusChangeListener();
    setComponentFocusChangeListener(view,listener);
  }
  listener.setEventHandler(focusChangeHandler);
}
