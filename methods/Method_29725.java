/** 
 * Sets the frame data received from the camera.  This adds the previous unused frame buffer (if present) back to the camera, and keeps a pending reference to the frame data for future use.
 */
private void setNextFrame(byte[] data,Camera camera){
synchronized (mLock) {
    if (mPendingFrameData != null) {
      camera.addCallbackBuffer(mPendingFrameData.array());
      mPendingFrameData=null;
    }
    if (!mBytesToByteBuffer.containsKey(data)) {
      Log.d(TAG,"Skipping frame.  Could not find ByteBuffer associated with the image " + "data from the camera.");
      return;
    }
    mPendingTimeMillis=android.os.SystemClock.elapsedRealtime() - mStartTimeMillis;
    mPendingFrameId++;
    mPendingFrameData=mBytesToByteBuffer.get(data);
    mLock.notifyAll();
  }
}
