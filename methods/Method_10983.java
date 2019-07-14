public void setAnimationType(int type){
  RxAdapterAnimator rxAdapterAnimator;
switch (type) {
case ALL_DOWN:
    rxAdapterAnimator=new RxAdapterAllMoveDownAnimator(this);
  break;
case UP_DOWN:
rxAdapterAnimator=new RxAdapterUpDownAnimator(this);
break;
default :
rxAdapterAnimator=new RxAdapterUpDownStackAnimator(this);
break;
}
setRxAdapterAnimator(rxAdapterAnimator);
}
