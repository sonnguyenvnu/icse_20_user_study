@Override protected void subscribeListener(Observer<? super Boolean> observer){
  if (!checkMainThread(observer)) {
    return;
  }
  Listener listener=new Listener(view,gravity,observer);
  observer.onSubscribe(listener);
  view.addDrawerListener(listener);
}
