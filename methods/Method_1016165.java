/** 
 * Initializes additional custom settings.
 */
protected void initialize(){
  onKeyPress(Hotkey.CTRL_SHIFT_Z,new KeyEventRunnable(){
    @Override public void run(    final KeyEvent e){
      redoLastAction();
    }
  }
);
}
