/** 
 * Installs this caret on a text component.
 * @param c The text component.  If this is not an {@link RTextArea}, an <code>Exception</code> will be thrown.
 */
@Override public void install(JTextComponent c){
  if (!(c instanceof RTextArea)) {
    throw new IllegalArgumentException("c must be instance of RTextArea");
  }
  super.install(c);
  c.setNavigationFilter(new FoldAwareNavigationFilter());
}
