public void snapshot(final Bitmap bitmap,final ISnapshotReadyCallback callback){
  mapView.queueEvent(new Runnable(){
    @Override public void run(){
      Bitmap surface=createBitmapFromGLSurface(0,0,mapView.getWidth(),mapView.getHeight(),GLAdapter.gl);
      final Bitmap result;
      if (bitmap != null) {
        Canvas c=new Canvas(bitmap);
        c.drawBitmap(surface,0,0,new Paint());
        result=bitmap;
      }
 else {
        result=surface;
      }
      mapView.getHandler().post(new Runnable(){
        @Override public void run(){
          Log.d(TAG,"snapshot result: " + result);
          try {
            callback.onBitmapReady(result);
          }
 catch (          RemoteException e) {
            Log.w(TAG,e);
          }
        }
      }
);
    }
  }
);
}
