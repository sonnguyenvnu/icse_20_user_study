public void setLocation(final int x,final int y){
  display.getEDTUtil().invoke(false,new Runnable(){
    @Override public void run(){
      window.setTopLevelPosition(x,y);
    }
  }
);
}
