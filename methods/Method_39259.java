/** 
 * Uses default  {@link Session}. Any property will be ignored.
 * @return this
 * @see System#getProperties()
 */
public T useDefaultSession(){
  this.session=Session.getDefaultInstance(System.getProperties());
  return _this();
}
