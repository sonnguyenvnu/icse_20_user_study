/** 
 * Set a ChainrInstantiator to use when instantiating Transform Objects. If one is not set, defaults to DefaultChainrInstantiator;
 * @param loader ChainrInstantiator to use load Transforms
 */
public ChainrBuilder loader(ChainrInstantiator loader){
  if (loader == null) {
    throw new IllegalArgumentException("ChainrBuilder requires a non-null loader.");
  }
  this.chainrInstantiator=loader;
  return this;
}
