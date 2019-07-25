/** 
 * Additional initializtion of WebWindow settings.
 */
protected void initialize(){
  setFocusable(true);
  SwingUtils.setOrientation(this);
  focusTracker=new DefaultFocusTracker(true){
    @Override public boolean isTrackingEnabled(){
      return isShowing() && closeOnFocusLoss;
    }
    @Override public void focusChanged(    final boolean focused){
      if (closeOnFocusLoss && isShowing() && !focused) {
        dispose();
      }
    }
  }
;
  FocusManager.addFocusTracker(this,focusTracker);
}
