/** 
 * ??????
 * @param registry RegistryConfig
 * @return the registry
 */
public S setRegistry(RegistryConfig registry){
  if (this.registry == null) {
    this.registry=new ArrayList<RegistryConfig>();
  }
  this.registry.add(registry);
  return castThis();
}
