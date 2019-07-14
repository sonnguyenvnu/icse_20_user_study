/** 
 * Serialize ISOChronology instances using a small stub. This reduces the serialized size, and deserialized instances come from the cache.
 */
private Object writeReplace(){
  return new Stub(getZone());
}
