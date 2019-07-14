/** 
 * Returns an observable that emits the accumulated list of paginated data each time a new page is loaded.
 */
private @NonNull Observable<List<Data>> dataWithPagination(final @NonNull Params firstPageParams){
  final Observable<List<Data>> data=paramsAndMoreUrlWithPagination(firstPageParams).concatMap(this::fetchData).takeUntil(List::isEmpty);
  final Observable<List<Data>> paginatedData=this.clearWhenStartingOver ? data.scan(new ArrayList<>(),this.concater) : data.scan(this.concater);
  return this.distinctUntilChanged ? paginatedData.distinctUntilChanged() : paginatedData;
}
