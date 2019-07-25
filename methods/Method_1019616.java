/** 
 * Called when the UI is being removed from the interface of a JTextComponent.  This is used to unregister any listeners that were attached.
 * @param c The text component.  If this is not an<code>RTextArea</code>, an <code>Exception</code> will be thrown.
 */
@Override public void deinstall(JTextComponent c){
  if (!(c instanceof RTextArea)) {
    throw new IllegalArgumentException("c must be instance of RTextArea");
  }
  super.deinstall(c);
  c.setNavigationFilter(null);
}
