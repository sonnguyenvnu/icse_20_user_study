private static void initialize(Context context){
  final int maxMemory=(int)(Runtime.getRuntime().maxMemory() / 1024);
  final int limit=Math.max(maxMemory / 10,1024);
  sCache=new LruCache<Long,Bitmap>(limit){
    @Override protected void entryRemoved(    boolean evicted,    Long key,    Bitmap oldValue,    Bitmap newValue){
      super.entryRemoved(evicted,key,oldValue,newValue);
      oldValue.recycle();
    }
    @Override protected int sizeOf(    Long key,    Bitmap value){
      final long sizeInBytes;
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        sizeInBytes=value.getAllocationByteCount();
      }
 else {
        sizeInBytes=value.getRowBytes() * value.getHeight();
      }
      return (int)(sizeInBytes / 1024);
    }
  }
;
  Resources res=context.getResources();
  sMaxImageSizePx=Math.round(res.getDisplayMetrics().density * MAX_CACHED_IMAGE_SIZE);
}
