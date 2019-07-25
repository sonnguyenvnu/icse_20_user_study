private final ByteBuffer reserve(int size){
  if (_currentBuffer.remaining() < size) {
    _currentBuffer.limit(_currentBuffer.position());
    _currentBuffer=allocateByteBuffer(size);
    _currentIndex++;
  }
  return _currentBuffer;
}
