/** 
 * Release a  {@link ByteBuffer} allocated by calling {@link #read(String)}.
 * @param byteBuffer The  {@link ByteBuffer} to release.
 */
public void release(final ByteBuffer byteBuffer){
  ReflectionUtils.invokeMethod(moduleReader,"release",ByteBuffer.class,byteBuffer,true);
}
