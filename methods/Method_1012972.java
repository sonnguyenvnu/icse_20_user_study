/** 
 * Removes this key and the associated value from this function if it is present (optional operation).
 * @param key the key.
 * @return the old value, or {@code null} if no value was present for the given key.
 * @see java.util.Map#remove(Object)
 */
default V remove(final Object key){
  throw new UnsupportedOperationException();
}
