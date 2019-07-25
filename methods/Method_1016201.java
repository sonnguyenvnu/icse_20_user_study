/** 
 * Additional initializtion of WebFrame settings.
 */
protected void initialize(){
  SwingUtils.setOrientation(this);
  focusTracker=new DefaultFocusTracker(true){
    @Override public boolean isTrackingEnabled(){
      return closeOnFocusLoss;
    }
    @Override public void focusChanged(    final boolean focused){
      if (closeOnFocusLoss && WebFrame.this.isShowing() && !focused) {
        setVisible(false);
      }
    }
  }
;
  FocusManager.addFocusTracker(this,focusTracker);
}
