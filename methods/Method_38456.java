/** 
 * Appends other buffer to this one.
 */
public Buffer append(final Buffer buffer){
  if (buffer.list.isEmpty()) {
    return buffer;
  }
  list.addAll(buffer.list);
  last=buffer.last;
  size+=buffer.size;
  return this;
}
