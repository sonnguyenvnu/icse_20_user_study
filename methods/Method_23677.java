/** 
 * Put a key/value pair in the JSONObject, but only if the key and the value are both non-null, and only if there is not already a member with that name.
 * @param key
 * @param value
 * @return {@code this}.
 * @throws RuntimeException if the key is a duplicate, or if{@link #put(String,Object)} throws.
 */
private JSONObject putOnce(String key,Object value){
  if (key != null && value != null) {
    if (this.opt(key) != null) {
      throw new RuntimeException("Duplicate key \"" + key + "\"");
    }
    this.put(key,value);
  }
  return this;
}
