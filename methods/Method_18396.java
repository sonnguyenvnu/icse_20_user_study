/** 
 * Installs the touch listeners that will dispatch the touch handler defined in the component's props.
 */
private static void setTouchHandler(EventHandler<TouchEvent> touchHandler,View view){
  if (touchHandler != null) {
    ComponentTouchListener listener=getComponentTouchListener(view);
    if (listener == null) {
      listener=new ComponentTouchListener();
      setComponentTouchListener(view,listener);
    }
    listener.setEventHandler(touchHandler);
  }
}
