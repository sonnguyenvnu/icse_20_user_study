public static void init(Context context){
  if (LOCAL_LOGV) {
    Timber.v("DefaultLayoutManager.init()");
  }
  if (sInstance != null) {
    Timber.w("Already initialized.");
  }
  sInstance=new LayoutManager(context);
}
