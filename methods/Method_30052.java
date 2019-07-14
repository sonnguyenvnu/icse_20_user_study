public static void init(Context context){
  if (BuildConfig.DEBUG) {
    return;
  }
  Fabric.with(context,new Crashlytics(),new Answers());
}
