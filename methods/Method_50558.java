@Override public void load(final String url,final ImageEngine.Callback callback){
  ThreadPool.run(new Runnable(){
    @Override public void run(){
      try {
        byte[] bytes=getBytesArrayFromNet(url);
        final Bitmap bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        final BitmapDrawable drawable=new BitmapDrawable(Resources.getSystem(),bitmap);
        new Handler(Looper.getMainLooper()).post(new Runnable(){
          @Override public void run(){
            callback.onCompleted(drawable);
          }
        }
);
      }
 catch (      Exception e) {
        e.printStackTrace();
      }
    }
  }
);
}
