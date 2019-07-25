/** 
 * Appends the instance to this list. Note that since memory for the Instance has already been allocated, no check is made to catch OutOfMemoryError.
 * @return <code>true</code> if successful
 */
@Override public boolean add(Instance instance){
  InstanceList page;
  if (this.size % this.instancesPerPage == 0) {
    int pageId=this.size / this.instancesPerPage;
    int bin=pageId % this.inMemoryPages.length;
    swapOut(this.inMemoryPageIds[bin]);
    page=new InstanceList(this.noopPipe);
    this.inMemoryPageIds[bin]=pageId;
    this.inMemoryPages[bin]=page;
  }
 else {
    page=getPageForIndex(this.size,true);
  }
  boolean ret=page.add(instance);
  if (ret) {
    this.size++;
  }
  return ret;
}
