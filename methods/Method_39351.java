/** 
 * Sets the type of cache used for storing the configurations for external types that are not part of the container. This affects usages of the methods like  {@link PetiteContainer#wire(Object)} and {@link PetiteContainer#invokeMethod(Object,Method)}.
 */
public void setExternalsCache(final TypeCache<BeanDefinition> typeCacheImplementation){
  this.externalsCache=typeCacheImplementation;
}
