/** 
 * {@inheritDoc}
 */
@Override public void update(final JRootPane c,final String key,final Value value,final Object... data){
  final Window window=CoreSwingUtils.getWindowAncestor(c);
  if (window instanceof Frame) {
    ((Frame)window).setTitle(getDefaultText(value,data));
  }
 else   if (window instanceof Dialog) {
    ((Dialog)window).setTitle(getDefaultText(value,data));
  }
}
