public static LocationState with(Context context){
  if (instance == null) {
    instance=new LocationState(context.getApplicationContext());
  }
  return instance;
}
