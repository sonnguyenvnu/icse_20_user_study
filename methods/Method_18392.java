/** 
 * Installs the long click listeners that will dispatch the click handler defined in the component's props. Unconditionally set the clickable flag on the view.
 */
private static void setLongClickHandler(EventHandler<LongClickEvent> longClickHandler,View view){
  if (longClickHandler != null) {
    ComponentLongClickListener listener=getComponentLongClickListener(view);
    if (listener == null) {
      listener=new ComponentLongClickListener();
      setComponentLongClickListener(view,listener);
    }
    listener.setEventHandler(longClickHandler);
    view.setLongClickable(true);
  }
}
