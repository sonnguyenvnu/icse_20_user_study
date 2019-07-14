/** 
 * Get this duration as an immutable <code>Duration</code> object.
 * @return a Duration created using the millisecond duration from this instance
 */
public Duration toDuration(){
  return new Duration(getMillis());
}
