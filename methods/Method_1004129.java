public boolean init(final List<? extends ITableItem> items,final ICoverageNode total){
  for (  final ITableItem i : items) {
    if (i.getNode().getCounter(entity).getTotalCount() > 0) {
      return true;
    }
  }
  return false;
}
