public void track(String trackingUrl,String videoUrl,float watched,float length){
  if (checkUrl(trackingUrl)) {
    Log.d(TAG,String.format("Start history tracking: %s, %s, %s, %s",trackingUrl,videoUrl,watched,length));
    HashMap<String,String> headers=mManager.getHeaders();
    final String fullTrackingUrl=processUrl(trackingUrl,videoUrl,watched,length);
    Log.d(TAG,"Full tracking url: " + fullTrackingUrl);
    Log.d(TAG,"Tracking headers: " + headers);
    new Thread(() -> {
      Response response=OkHttpHelpers.doGetOkHttpRequest(fullTrackingUrl,headers);
      Log.d(TAG,"Tracking response: " + response);
    }
).start();
  }
 else {
    Log.d(TAG,"This tracking url isn't supported: " + trackingUrl);
  }
}
