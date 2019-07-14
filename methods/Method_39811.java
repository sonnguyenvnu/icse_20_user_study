/** 
 * Adds an aspect.
 */
public T withAspect(final A proxyAspect){
  proxyAspectList.add(proxyAspect);
  return _this();
}
