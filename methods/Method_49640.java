private void addOrderToQuery(SolrQuery solrQuery,List<IndexQuery.OrderEntry> orders){
  for (  final IndexQuery.OrderEntry order1 : orders) {
    final String item=order1.getKey();
    final SolrQuery.ORDER order=order1.getOrder() == Order.ASC ? SolrQuery.ORDER.asc : SolrQuery.ORDER.desc;
    solrQuery.addSort(new SolrQuery.SortClause(item,order));
  }
}
