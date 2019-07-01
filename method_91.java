/** 
 * Append a list of ByteBuffers to this stream. 
 */
public void _XXXXX_(List<ByteBuffer> lists){
  for (  ByteBuffer buffer : lists) {
    buffer.position(buffer.limit());
  }
  buffers.addAll(lists);
}