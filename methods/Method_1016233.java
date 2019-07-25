/** 
 * {@inheritDoc}
 */
@Override public void install(final E c){
  super.install(c);
  focusTracker=new DefaultFocusTracker(){
    @Override public boolean isTrackingEnabled(){
      return !undecorated && paintFocus;
    }
    @Override public void focusChanged(    final boolean focused){
      NPLabelPainter.this.focused=focused;
      repaint();
    }
  }
;
  FocusManager.addFocusTracker(c,focusTracker);
}
