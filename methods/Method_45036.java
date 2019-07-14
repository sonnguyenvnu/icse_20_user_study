private void setQuitOnWindowClosing(){
  this.addWindowListener(new WindowAdapter(){
    @Override public void windowClosing(    WindowEvent e){
      quit();
    }
  }
);
}
