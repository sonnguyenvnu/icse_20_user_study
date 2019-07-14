/** 
 * Returns if the cache should bypass the read buffer. 
 */
boolean skipReadBuffer(){
  return fastpath() && frequencySketch().isNotInitialized();
}
