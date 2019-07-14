/** 
 * Initializes the buffer.
 * @param timeUs The presentation timestamp for the buffer, in microseconds.
 * @param size An upper bound on the size of the data that will be written to the buffer.
 * @return The {@link #data} buffer, for convenience.
 */
public ByteBuffer init(long timeUs,int size){
  this.timeUs=timeUs;
  if (data == null || data.capacity() < size) {
    data=ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder());
  }
  data.position(0);
  data.limit(size);
  return data;
}
