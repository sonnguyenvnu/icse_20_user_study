/** 
 * Create a new free-list page.
 * @param store the page store
 * @param pageId the page id
 * @return the page
 */
static PageFreeList create(PageStore store,int pageId){
  int pageCount=(store.getPageSize() - DATA_START) * 8;
  BitSet used=new BitSet(pageCount);
  used.set(0);
  return new PageFreeList(store,pageId,pageCount,used);
}
