/** 
 * Returns number of bytes between the start position (inclusive) and end position (exclusive). <p> The offset is zero if the specified start and end positions reference the same position. The offset is one if the end position is one position more advanced then the start position. <p>
 * @param startPos provides the start position (inclusive).
 * @param endPos provides the end position (exclusive).
 * @return the number of bytes between the start position (inclusive) and end position (exclusive).
 */
public int offset(Position startPos,Position endPos){
  if (startPos._bufferChain != this || endPos._bufferChain != this) {
    throw new IllegalArgumentException("Position does not apply to this BufferChain");
  }
  if ((startPos._index > endPos._index) || (startPos._index == endPos._index && startPos._position > endPos._position)) {
    throw new IllegalArgumentException("Start position is greater than end position");
  }
  int sum;
  if (startPos._index == endPos._index) {
    sum=(endPos._position - startPos._position);
  }
 else {
    int index=startPos._index;
    sum=_bufferList.get(index).limit() - startPos._position;
    index++;
    while (index < endPos._index) {
      sum+=_bufferList.get(index).limit();
      index++;
    }
    sum+=endPos._position;
  }
  return sum;
}
