/** 
 * If this component instance had its layout created on a different thread, we need to create a copy to create its layout on this thread, otherwise we'll end up accessing the internal data structures of the same instance on different threads. This can happen when the component is passed as a prop and the same instance can be used in layout calculations on main and background threads. https://github.com/facebook/litho/issues/360
 */
Component getThreadSafeInstance(){
  if (mLayoutVersionGenerator == null) {
    return this;
  }
  final boolean shouldCreateNewInstance=mLayoutVersionGenerator.getAndSet(true);
  return shouldCreateNewInstance ? makeShallowCopy() : this;
}
