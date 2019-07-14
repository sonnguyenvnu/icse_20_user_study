/** 
 * Passes the  {@code mLookAt} field to the specified {@link java.util.function.Consumer Consumer}. 
 */
public AICamera mLookAt(java.util.function.Consumer<AIVector3D> consumer){
  consumer.accept(mLookAt());
  return this;
}
