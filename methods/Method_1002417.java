/** 
 * Rewind the buffer chain, i.e. set the current position to the beginning of the buffer chain.
 * @return {@code this}.
 */
public BufferChain rewind(){
  for (  ByteBuffer buffer : _bufferList) {
    buffer.rewind();
  }
  _currentIndex=0;
  _currentBuffer=_bufferList.get(_currentIndex);
  return this;
}
