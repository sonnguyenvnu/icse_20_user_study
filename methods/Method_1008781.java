/** 
 * Inserts a Parcelable value into the mapping of the underlying Bundle, replacing any existing value for the given key. Either key or value may be null.
 * @param key a String, or null
 * @param value a Parcelable object, or null
 * @return this bundler instance to chain method calls
 */
public Bundler put(String key,Parcelable value){
  delegate.putParcelable(key,value);
  return this;
}
