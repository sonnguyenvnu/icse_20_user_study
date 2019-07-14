/** 
 * Passes the  {@code mName} field to the specified {@link java.util.function.Consumer Consumer}. 
 */
public AIMesh mName(java.util.function.Consumer<AIString> consumer){
  consumer.accept(mName());
  return this;
}
