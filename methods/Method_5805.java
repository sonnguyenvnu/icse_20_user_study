/** 
 * Returns the number of bytes that are still to be read for the current  {@link DataSpec}. <p> If the total length of the data being read is known, then this length minus  {@code bytesRead()}is returned. If the total length is unknown,  {@link C#LENGTH_UNSET} is returned.
 * @return The remaining length, or {@link C#LENGTH_UNSET}.
 */
protected final long bytesRemaining(){
  return bytesToRead == C.LENGTH_UNSET ? bytesToRead : bytesToRead - bytesRead;
}
