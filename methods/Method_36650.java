@Override protected void onStart(){
  super.onStart();
  mActivityLFEventLifeCycleProvider.emitNext(ActivityLFEvent.START);
  Observable.interval(1,TimeUnit.SECONDS).doOnDispose(new Action(){
    @Override public void run() throws Exception {
      Log.i(TAG,"Unsubscribing subscription from onStart()");
    }
  }
).compose(mActivityLFEventLifeCycleProvider.<Long>bindUntil(ActivityLFEvent.DESTROY)).subscribe(new Consumer<Long>(){
    @Override public void accept(    Long num) throws Exception {
      Log.i(TAG,"Started in onStart(), running until in onDestroy(): " + num);
    }
  }
);
}
