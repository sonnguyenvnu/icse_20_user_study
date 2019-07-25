/** 
 * Put an element into the set of (name, value) pairs for this instance. If there is a preexisting element with the same name, it will be replaced by this method.
 * @param pair {@code non-null;} the (name, value) pair to place into this instance
 */
public void put(NameValuePair pair){
  throwIfImmutable();
  if (pair == null) {
    throw new NullPointerException("pair == null");
  }
  elements.put(pair.getName(),pair);
}
