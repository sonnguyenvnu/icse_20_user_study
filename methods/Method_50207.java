@Override public void onNext(T t){
  try {
    onEvent(t);
  }
 catch (  Exception e) {
    e.printStackTrace();
    onError(e);
  }
}
