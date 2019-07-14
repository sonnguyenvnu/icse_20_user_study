private static QueryAction handleSelect(Client client,Select select){
  if (select.isAgg) {
    return new AggregationQueryAction(client,select);
  }
 else {
    return new DefaultQueryAction(client,select);
  }
}
