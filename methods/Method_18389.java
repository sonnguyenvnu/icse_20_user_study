/** 
 * Installs the click listeners that will dispatch the click handler defined in the component's props. Unconditionally set the clickable flag on the view.
 */
private static void setClickHandler(EventHandler<ClickEvent> clickHandler,View view){
  if (clickHandler == null) {
    return;
  }
  ComponentClickListener listener=getComponentClickListener(view);
  if (listener == null) {
    listener=new ComponentClickListener();
    setComponentClickListener(view,listener);
  }
  listener.setEventHandler(clickHandler);
  view.setClickable(true);
}
