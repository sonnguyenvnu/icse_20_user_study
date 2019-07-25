@Override public void run(){
  NetworkInfo activeNetwork=cm.getActiveNetworkInfo();
  boolean isConnected=activeNetwork != null && activeNetwork.isConnectedOrConnecting();
  if (isConnected) {
    URLConnection con=null;
    InputStream is=null;
    try {
      URL url=new URL(URL);
      con=url.openConnection();
      is=con.getInputStream();
      final BitmapFactory.Options options=new BitmapFactory.Options();
      options.inJustDecodeBounds=true;
      Bitmap bitmap=BitmapFactory.decodeStream(is);
      overlay.setImage(bitmap);
      overlay.setPosition(new GeoPoint(50.0,-127.5),new GeoPoint(21.0,-66.5));
      Activity act=getActivity();
      if (act != null)       act.runOnUiThread(new Runnable(){
        @Override public void run(){
          mMapView.getOverlayManager().add(overlay);
        }
      }
);
    }
 catch (    Throwable e) {
      Log.e(TAG,"error fetching image",e);
    }
 finally {
      if (is != null)       try {
        is.close();
      }
 catch (      IOException e) {
      }
    }
  }
}
