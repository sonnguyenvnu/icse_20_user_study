public static BellUtil with(Context context){
  if (instance == null) {
synchronized (lock) {
      if (instance == null) {
        instance=new BellUtil((Vibrator)context.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE));
      }
    }
  }
  return instance;
}
