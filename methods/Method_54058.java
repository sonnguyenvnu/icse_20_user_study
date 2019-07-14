/** 
 * Passes the  {@code mNodeName} field to the specified {@link java.util.function.Consumer Consumer}. 
 */
public AINodeAnim mNodeName(java.util.function.Consumer<AIString> consumer){
  consumer.accept(mNodeName());
  return this;
}
