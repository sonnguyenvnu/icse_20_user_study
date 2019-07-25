/** 
 * Allocate the required pages so that no pages need to be allocated while writing.
 * @param minBuffer the number of bytes to allocate
 */
void reserve(int minBuffer){
  if (reserved < minBuffer) {
    int pageSize=store.getPageSize();
    int capacityPerPage=PageStreamData.getCapacity(pageSize);
    int pages=PageStreamTrunk.getPagesAddressed(pageSize);
    int pagesToAllocate=0, totalCapacity=0;
    do {
      pagesToAllocate+=pages + 1;
      totalCapacity+=pages * capacityPerPage;
    }
 while (totalCapacity < minBuffer);
    int firstPageToUse=atEnd ? trunkPageId : 0;
    store.allocatePages(reservedPages,pagesToAllocate,exclude,firstPageToUse);
    reserved+=totalCapacity;
    if (data == null) {
      initNextData();
    }
  }
}
