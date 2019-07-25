/** 
 * Repositions this stream to the position at the time the mark() method was last called on this input stream. If mark() has not been called this method repositions the stream to its beginning.
 */
@Override public void reset(){
  if (_marked_offset == 0 && _marked_offset_count == 0) {
    _current_block_count=_marked_offset_count;
    _current_offset=_marked_offset;
    _data=_document.getBlockIterator();
    _buffer=null;
    return;
  }
  _data=_document.getBlockIterator();
  _current_offset=0;
  for (int i=0; i < _marked_offset_count; i++) {
    _buffer=_data.next();
    _current_offset+=_buffer.remaining();
  }
  _current_block_count=_marked_offset_count;
  if (_current_offset != _marked_offset) {
    _buffer=_data.next();
    _current_block_count++;
    int skipBy=_marked_offset - _current_offset;
    _buffer.position(_buffer.position() + skipBy);
  }
  _current_offset=_marked_offset;
}
