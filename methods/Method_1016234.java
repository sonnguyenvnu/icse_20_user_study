/** 
 * {@inheritDoc}
 */
@Override public void install(final E c){
  super.install(c);
  animated=false;
  focusTracker=new DefaultFocusTracker(){
    @Override public void focusChanged(    final boolean focused){
      NPScrollBarPainter.this.focused=focused;
      repaint();
    }
  }
;
  FocusManager.addFocusTracker(c,focusTracker);
}
