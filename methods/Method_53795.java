/** 
 * Passes the  {@code mUp} field to the specified {@link java.util.function.Consumer Consumer}. 
 */
public AICamera mUp(java.util.function.Consumer<AIVector3D> consumer){
  consumer.accept(mUp());
  return this;
}
