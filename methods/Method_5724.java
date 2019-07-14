/** 
 * Returns the data written to the sink since the last call to  {@link #open(DataSpec)}, or null if {@link #open(DataSpec)} has never been called.
 */
public byte[] getData(){
  return stream == null ? null : stream.toByteArray();
}
