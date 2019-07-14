/** 
 * Retrieves a copy of currently buffered frame.
 * @return current frame
 */
public Bitmap getCurrentFrame(){
  final Bitmap copy=mBuffer.copy(mBuffer.getConfig(),mBuffer.isMutable());
  copy.setHasAlpha(mBuffer.hasAlpha());
  return copy;
}
