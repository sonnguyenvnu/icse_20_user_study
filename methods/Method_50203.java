public static RxBus getDefault(){
  if (mInstance == null) {
synchronized (RxBus.class) {
      if (mInstance == null) {
        mInstance=new RxBus();
      }
    }
  }
  return mInstance;
}
