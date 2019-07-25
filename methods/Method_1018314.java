public static AppRate with(Context context){
  if (singleton == null) {
synchronized (AppRate.class) {
      if (singleton == null) {
        singleton=new AppRate(context);
      }
    }
  }
  return singleton;
}
