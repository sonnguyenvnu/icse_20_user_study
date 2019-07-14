/** 
 * Registers additional consumers.
 */
public Consumers<T> addAll(final Consumer<T>... consumers){
  Collections.addAll(consumerList,consumers);
  return this;
}
