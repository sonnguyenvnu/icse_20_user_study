/** 
 * Start retrieving and drawing liveview frame data by new threads.
 * @return true if the starting is completed successfully, false otherwise.
 * @see SimpleLiveviewSurfaceView#bindRemoteApi(SimpleRemoteApi)
 */
public boolean start(final String streamUrl,StreamFrameListener frameListener,StreamErrorListener errorListener){
  mErrorListener=errorListener;
  mFrameListener=frameListener;
  rotateMatrix.reset();
  rotateMatrix.postRotate(90);
  if (streamUrl == null) {
    Log.e(TAG,"start() streamUrl is null.");
    mWhileFetching=false;
    mErrorListener.onError(StreamErrorListener.StreamErrorReason.OPEN_ERROR);
    return false;
  }
  if (mWhileFetching) {
    Log.w(TAG,"start() already starting.");
    return false;
  }
  mWhileFetching=true;
  new Thread(){
    @Override public void run(){
      Log.d(TAG,"Starting retrieving streaming data from server.");
      SimpleLiveviewSlicer slicer=null;
      try {
        slicer=new SimpleLiveviewSlicer();
        slicer.open(streamUrl);
        while (mWhileFetching) {
          final Payload payload=slicer.nextPayload();
          if (payload == null) {
            Log.e(TAG,"Liveview Payload is null.");
            continue;
          }
          if (mJpegQueue.size() == 2) {
            mJpegQueue.remove();
          }
          mJpegQueue.add(payload.jpegData);
          if (mFrameListener != null) {
            mFrameListener.onFrameAvailable(payload.jpegData);
          }
        }
      }
 catch (      IOException e) {
        Log.w(TAG,"IOException while fetching: " + e.getMessage());
        mErrorListener.onError(StreamErrorListener.StreamErrorReason.IO_EXCEPTION);
      }
 finally {
        if (slicer != null) {
          slicer.close();
        }
        if (mDrawerThread != null) {
          mDrawerThread.interrupt();
        }
        mJpegQueue.clear();
        mWhileFetching=false;
      }
    }
  }
.start();
  mDrawerThread=new Thread(){
    @Override public void run(){
      Log.d(TAG,"Starting drawing stream frame.");
      Bitmap frameBitmap=null;
      BitmapFactory.Options factoryOptions=new BitmapFactory.Options();
      factoryOptions.inSampleSize=1;
      if (mInMutableAvailable) {
        initInBitmap(factoryOptions);
      }
      while (mWhileFetching) {
        try {
          byte[] jpegData=mJpegQueue.take();
          frameBitmap=BitmapFactory.decodeByteArray(jpegData,0,jpegData.length,factoryOptions);
          if (frameBitmap != null) {
            Bitmap rotatedBitmap=Bitmap.createBitmap(frameBitmap,0,0,frameBitmap.getWidth(),frameBitmap.getHeight(),rotateMatrix,true);
            frameBitmap.recycle();
            frameBitmap=rotatedBitmap;
          }
        }
 catch (        IllegalArgumentException e) {
          if (mInMutableAvailable) {
            clearInBitmap(factoryOptions);
          }
          continue;
        }
catch (        InterruptedException e) {
          Log.i(TAG,"Drawer thread is Interrupted.");
          break;
        }
        if (mInMutableAvailable) {
          setInBitmap(factoryOptions,frameBitmap);
        }
        drawFrame(frameBitmap);
      }
      if (frameBitmap != null) {
        frameBitmap.recycle();
      }
      mWhileFetching=false;
    }
  }
;
  mDrawerThread.start();
  return true;
}
