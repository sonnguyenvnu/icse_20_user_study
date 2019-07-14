/** 
 * Wrapper of  {@link pl.droidsonroids.gif.GifDrawable#GifDrawable(java.nio.ByteBuffer)}
 * @param byteBuffer data source
 * @return this builder instance, to chain calls
 */
public T from(ByteBuffer byteBuffer){
  mInputSource=new InputSource.DirectByteBufferSource(byteBuffer);
  return self();
}
