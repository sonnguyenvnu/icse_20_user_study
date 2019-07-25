/** 
 * Indicate whether this  {@code MediaType} includes the given media type.<p>For instance,  {@code text/*} includes {@code text/plain} and {@code text/html}, and  {@code application/*+xml}includes  {@code application/soap+xml}, etc. This method is <b>not</b> symmetric.
 * @param other the reference media type with which to compare.
 * @return {@code true} if this media type includes the given media type; {@code false} otherwise.
 */
public boolean includes(MediaType other){
  return super.includes(other);
}
