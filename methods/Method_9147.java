private void reloadFrames(int frameNum){
  if (mediaMetadataRetriever == null) {
    return;
  }
  if (frameNum == 0) {
    if (isRoundFrames) {
      frameHeight=frameWidth=AndroidUtilities.dp(56);
      framesToLoad=(int)Math.ceil((getMeasuredWidth() - AndroidUtilities.dp(16)) / (frameHeight / 2.0f));
    }
 else {
      frameHeight=AndroidUtilities.dp(40);
      framesToLoad=(getMeasuredWidth() - AndroidUtilities.dp(16)) / frameHeight;
      frameWidth=(int)Math.ceil((float)(getMeasuredWidth() - AndroidUtilities.dp(16)) / (float)framesToLoad);
    }
    frameTimeOffset=videoLength / framesToLoad;
  }
  currentTask=new AsyncTask<Integer,Integer,Bitmap>(){
    @Override protected Bitmap doInBackground(    Integer... objects){
      frameNum=objects[0];
      Bitmap bitmap=null;
      if (isCancelled()) {
        return null;
      }
      try {
        bitmap=mediaMetadataRetriever.getFrameAtTime(frameTimeOffset * frameNum * 1000,MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
        if (isCancelled()) {
          return null;
        }
        if (bitmap != null) {
          Bitmap result=Bitmap.createBitmap(frameWidth,frameHeight,bitmap.getConfig());
          Canvas canvas=new Canvas(result);
          float scaleX=(float)frameWidth / (float)bitmap.getWidth();
          float scaleY=(float)frameHeight / (float)bitmap.getHeight();
          float scale=scaleX > scaleY ? scaleX : scaleY;
          int w=(int)(bitmap.getWidth() * scale);
          int h=(int)(bitmap.getHeight() * scale);
          Rect srcRect=new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
          Rect destRect=new Rect((frameWidth - w) / 2,(frameHeight - h) / 2,w,h);
          canvas.drawBitmap(bitmap,srcRect,destRect,null);
          bitmap.recycle();
          bitmap=result;
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      return bitmap;
    }
    @Override protected void onPostExecute(    Bitmap bitmap){
      if (!isCancelled()) {
        frames.add(bitmap);
        invalidate();
        if (frameNum < framesToLoad) {
          reloadFrames(frameNum + 1);
        }
      }
    }
  }
;
  currentTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,frameNum,null,null);
}
