@Override public void clear(){
  int numPages=this.size / this.instancesPerPage;
  for (int i=0; i <= numPages; i++) {
    getFileForPage(i).delete();
  }
  for (int i=0; i < this.inMemoryPages.length; i++) {
    this.inMemoryPages[i]=null;
    this.inMemoryPageIds[i]=-1;
  }
  this.size=0;
  this.swapIns=0;
  this.swapInTime=0;
  this.swapOuts=0;
  this.swapOutTime=0;
  this.dirty.clear();
  super.clear();
}
