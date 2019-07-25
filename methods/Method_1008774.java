public final void prepare(@Nullable final OnPrepareFinishListener l){
  isPrepared=false;
  reset();
  mIPieView.getPieView().post(new Runnable(){
    @Override public void run(){
      isPrepared=onPrepare();
      if (isPrepared) {
        handlePrepareFinish(l);
      }
    }
  }
);
}
