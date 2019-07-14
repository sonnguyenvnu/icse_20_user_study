public void showCursor(){
  display.getEDTUtil().invoke(false,new Runnable(){
    @Override public void run(){
      window.setPointerVisible(true);
    }
  }
);
}
