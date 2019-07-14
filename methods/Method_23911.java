/** 
 * Show or hide the window. 
 */
@Override public void setVisible(final boolean visible){
  Platform.runLater(new Runnable(){
    public void run(){
      if (visible) {
        stage.show();
        canvas.requestFocus();
      }
 else {
        stage.hide();
      }
    }
  }
);
}
