/** 
 * ??????"??????"??
 */
private void initRxBus(){
  Disposable subscribe=RxBus.getDefault().toObservable(RxCodeConstants.JUMP_TYPE_TO_ONE,RxBusBaseMessage.class).subscribe(new Consumer<RxBusBaseMessage>(){
    @Override public void accept(    RxBusBaseMessage rxBusBaseMessage) throws Exception {
      setCurrentItem(2);
    }
  }
);
  addSubscription(subscribe);
}
