/** 
 * Returns a  {@link ServiceBindingBuilder} which is for binding a {@link Service} fluently.
 */
public ServiceBindingBuilder route(){
  return new ServiceBindingBuilder(this);
}
