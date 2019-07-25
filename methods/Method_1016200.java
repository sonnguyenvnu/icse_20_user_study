/** 
 * Additional initializtion of WebDialog settings.
 */
protected void initialize(){
  SwingUtils.setOrientation(this);
  setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  focusTracker=new DefaultFocusTracker(true){
    @Override public boolean isTrackingEnabled(){
      return isShowing() && closeOnFocusLoss;
    }
    @Override public void focusChanged(    final boolean focused){
      if (closeOnFocusLoss && isShowing() && !focused) {
        processWindowEvent(new WindowEvent(WebDialog.this,WindowEvent.WINDOW_CLOSING));
      }
    }
  }
;
  FocusManager.addFocusTracker(this,focusTracker);
}
