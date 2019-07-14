/** 
 * Serializes object into source.
 */
public String serialize(final Object source){
  FastCharBuffer fastCharBuffer=new FastCharBuffer();
  serialize(source,fastCharBuffer);
  return fastCharBuffer.toString();
}
