/** 
 * Set the current position to the specified  {@link Position}.
 * @param pos provides the new current position.
 * @return {@code this}.
 */
public BufferChain position(Position pos){
  if (pos._bufferChain != this) {
    throw new IllegalArgumentException("Position does not apply to this BufferChain");
  }
  _currentIndex=pos._index;
  _currentBuffer=_bufferList.get(_currentIndex);
  if (pos._position == _currentBuffer.limit() && _currentIndex < (_bufferList.size() - 1)) {
    _currentIndex++;
    _currentBuffer=_bufferList.get(_currentIndex);
    _currentBuffer.position(0);
  }
 else {
    _currentBuffer.position(pos._position);
  }
  return this;
}
