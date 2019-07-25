/** 
 * Replaces the <code>Instance</code> at position <code>index</code> with a new one. Note that this is the only sanctioned way of changing an Instance. 
 */
@Override public Instance set(int index,Instance instance){
  InstanceList page=getPageForIndex(index,true);
  return page.set(index % this.instancesPerPage,instance);
}
