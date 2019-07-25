/** 
 * Returns a  {@link ServiceBindingBuilder} which is for binding a {@link Service} fluently.
 */
public VirtualHostServiceBindingBuilder route(){
  return new VirtualHostServiceBindingBuilder(this);
}
