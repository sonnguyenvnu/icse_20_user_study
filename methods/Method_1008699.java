@Override public GeoDistanceSortBuilder rewrite(QueryRewriteContext ctx) throws IOException {
  if (nestedFilter == null && nestedSort == null) {
    return this;
  }
  if (nestedFilter != null) {
    QueryBuilder rewrite=nestedFilter.rewrite(ctx);
    if (nestedFilter == rewrite) {
      return this;
    }
    return new GeoDistanceSortBuilder(this).setNestedFilter(rewrite);
  }
 else {
    NestedSortBuilder rewrite=nestedSort.rewrite(ctx);
    if (nestedSort == rewrite) {
      return this;
    }
    return new GeoDistanceSortBuilder(this).setNestedSort(rewrite);
  }
}
