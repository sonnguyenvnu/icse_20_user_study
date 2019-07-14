@Override public void initEvent(){
  super.initEvent();
  setOnStopLoadListener(this);
  lvBaseList.setXListViewListener(this);
}
