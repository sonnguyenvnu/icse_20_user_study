public void setSupportRx(boolean supportRx){
  if (supportRx) {
    if (!(mDefaultTimer instanceof RxTimer)) {
      mDefaultTimer=new RxTimer(MILLISECOND);
    }
  }
 else {
    if (!(mDefaultTimer instanceof HandlerTimer)) {
      mDefaultTimer=new HandlerTimer(MILLISECOND);
    }
  }
}
