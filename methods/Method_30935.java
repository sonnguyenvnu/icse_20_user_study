public static void putBytes(final String key,final byte[] value,final Context context){
  executeAsync(new Runnable(){
    @Override public void run(){
      DiskCache diskCache=get(context);
      if (diskCache != null) {
        diskCache.putBytes(key,value);
      }
    }
  }
);
}
