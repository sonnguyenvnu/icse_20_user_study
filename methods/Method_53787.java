/** 
 * Passes the  {@code mName} field to the specified {@link java.util.function.Consumer Consumer}. 
 */
public AIBone mName(java.util.function.Consumer<AIString> consumer){
  consumer.accept(mName());
  return this;
}
