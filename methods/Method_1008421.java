/** 
 * Change the size of this array. Content between indexes <code>0</code> and <code>min(size(), newSize)</code> will be preserved. 
 */
@Override public void resize(long newSize){
  final int numPages=numPages(newSize);
  if (numPages > pages.length) {
    pages=Arrays.copyOf(pages,ArrayUtil.oversize(numPages,RamUsageEstimator.NUM_BYTES_OBJECT_REF));
  }
  for (int i=numPages - 1; i >= 0 && pages[i] == null; --i) {
    pages[i]=newLongPage(i);
  }
  for (int i=numPages; i < pages.length && pages[i] != null; ++i) {
    pages[i]=null;
    releasePage(i);
  }
  this.size=newSize;
}
