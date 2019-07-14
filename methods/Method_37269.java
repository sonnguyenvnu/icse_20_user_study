/** 
 * Excludes all properties, i.e. enables blacklist mode.
 */
public T excludeAll(){
  blacklist=false;
  return _this();
}
