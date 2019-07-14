public void hideCursor(){
  display.getEDTUtil().invoke(false,new Runnable(){
    @Override public void run(){
      window.setPointerVisible(false);
    }
  }
);
}
