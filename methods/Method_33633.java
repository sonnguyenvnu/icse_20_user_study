/** 
 * ??????"??"??
 */
private void initRxBus(){
  Disposable subscribe=RxBus.getDefault().toObservable(RxCodeConstants.JUMP_TYPE,Integer.class).subscribe(new Consumer<Integer>(){
    @Override public void accept(    Integer integer) throws Exception {
      if (integer == 0) {
        bindingView.vpGank.setCurrentItem(3);
      }
 else       if (integer == 1) {
        bindingView.vpGank.setCurrentItem(1);
      }
 else       if (integer == 2) {
        bindingView.vpGank.setCurrentItem(2);
      }
    }
  }
);
  addSubscription(subscribe);
}
