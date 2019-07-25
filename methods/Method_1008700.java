public NestedSortBuilder rewrite(QueryRewriteContext ctx) throws IOException {
  if (filter == null && nestedSort == null) {
    return this;
  }
  QueryBuilder rewriteFilter=this.filter;
  NestedSortBuilder rewriteNested=this.nestedSort;
  if (filter != null) {
    rewriteFilter=filter.rewrite(ctx);
  }
  if (nestedSort != null) {
    rewriteNested=nestedSort.rewrite(ctx);
  }
  if (rewriteFilter != this.filter || rewriteNested != this.nestedSort) {
    return new NestedSortBuilder(this.path).setFilter(rewriteFilter).setNestedSort(rewriteNested);
  }
 else {
    return this;
  }
}
