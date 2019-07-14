public static BinderPool getInsance(Context context){
  if (sInstance == null) {
synchronized (BinderPool.class) {
      if (sInstance == null) {
        sInstance=new BinderPool(context);
      }
    }
  }
  return sInstance;
}
