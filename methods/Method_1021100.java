public PaginationWrapper paginate(final int offset,final int limit,final String path,final ItemsSupplier itemsSupplier,final Supplier<Integer> countSupplier){
  final List items=itemsSupplier.queryOneMore(offset,limit);
  final PaginationLinks paginationLinks;
  if (items.isEmpty() && offset != 0 && limit != 0) {
    final int count=countSupplier.get();
    int latestOffset=count / limit;
    if (offset >= latestOffset) {
      latestOffset=0;
    }
    paginationLinks=createLinks(path,latestOffset,limit,items.size());
  }
 else {
    paginationLinks=createLinks(path,offset,limit,items.size());
    if (items.size() > limit) {
      items.remove(items.size() - 1);
    }
  }
  return new PaginationWrapper(items,paginationLinks);
}
