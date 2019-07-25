public boolean init(final List<? extends ITableItem> items,final ICoverageNode total){
  this.max=0;
  for (  final ITableItem item : items) {
    final int count=item.getNode().getCounter(entity).getTotalCount();
    if (count > this.max) {
      this.max=count;
    }
  }
  return true;
}
