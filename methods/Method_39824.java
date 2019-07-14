/** 
 * Returns raw bytecode.
 */
protected byte[] toByteArray(){
  assertProxyIsCreated();
  return destClassWriter.toByteArray();
}
