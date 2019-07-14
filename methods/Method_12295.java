/** 
 * Like  {@link #seekTo(int)} but performs operation synchronously and returns that frame.
 * @param position position to seek to in milliseconds
 * @return frame at desired position
 * @throws IndexOutOfBoundsException if position&lt;0
 */
public Bitmap seekToPositionAndGet(@IntRange(from=0,to=Integer.MAX_VALUE) final int position){
  if (position < 0) {
    throw new IllegalArgumentException("Position is not positive");
  }
  final Bitmap bitmap;
synchronized (mNativeInfoHandle) {
    mNativeInfoHandle.seekToTime(position,mBuffer);
    bitmap=getCurrentFrame();
  }
  mInvalidationHandler.sendEmptyMessageAtTime(MSG_TYPE_INVALIDATION,0);
  return bitmap;
}
