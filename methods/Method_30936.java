public static void putString(final String key,final String value,final Context context){
  executeAsync(new Runnable(){
    @Override public void run(){
      DiskCache diskCache=get(context);
      if (diskCache != null) {
        diskCache.putString(key,value);
      }
    }
  }
);
}
