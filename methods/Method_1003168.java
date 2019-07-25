/** 
 * Initialize the page.
 * @param page1 the first child page
 * @param pivot the pivot key
 * @param page2 the last child page
 */
void init(PageBtree page1,SearchRow pivot,PageBtree page2){
  entryCount=0;
  childPageIds=new int[]{page1.getPos()};
  rows=SearchRow.EMPTY_ARRAY;
  offsets=Utils.EMPTY_INT_ARRAY;
  addChild(0,page2.getPos(),pivot);
  if (pageStoreInternalCount) {
    rowCount=page1.getRowCount() + page2.getRowCount();
  }
  check();
}
