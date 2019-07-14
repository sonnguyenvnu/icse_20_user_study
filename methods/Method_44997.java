private void setSaveWindowPositionOnClosing(){
  this.addWindowListener(new WindowAdapter(){
    @Override public void windowDeactivated(    WindowEvent e){
      WindowPosition windowPosition=ConfigSaver.getLoadedInstance().getFindWindowPosition();
      windowPosition.readPositionFromDialog(FindAllBox.this);
    }
  }
);
}
