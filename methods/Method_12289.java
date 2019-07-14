/** 
 * See  {@link GifDrawable#seekTo(int)}
 * @param position position to seek to in milliseconds
 * @param buffer   the frame buffer
 * @throws IllegalArgumentException if {@code position < 0 }or  {@code buffer} is recycled
 */
public void seekToTime(@IntRange(from=0,to=Integer.MAX_VALUE) final int position,@NonNull final Bitmap buffer){
  checkBuffer(buffer);
  mGifInfoHandle.seekToTime(position,buffer);
}
