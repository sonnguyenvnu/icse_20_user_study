@Override public SqlElasticSearchRequestBuilder explain() throws SqlParseException {
  this.request=new SearchRequestBuilder(client,SearchAction.INSTANCE);
  setIndicesAndTypes();
  setWhere(select.getWhere());
  AggregationBuilder lastAgg=null;
  for (  List<Field> groupBy : select.getGroupBys()) {
    if (!groupBy.isEmpty()) {
      Field field=groupBy.get(0);
      lastAgg=getGroupAgg(field,select);
      if (lastAgg != null && lastAgg instanceof TermsAggregationBuilder && !(field instanceof MethodField)) {
        if (select.getRowCount() < 200) {
          ((TermsAggregationBuilder)lastAgg).shardSize(5000);
          for (          Hint hint : select.getHints()) {
            if (hint.getType() == HintType.SHARD_SIZE) {
              if (hint.getParams() != null && hint.getParams().length != 0 && hint.getParams()[0] != null) {
                ((TermsAggregationBuilder)lastAgg).shardSize((Integer)hint.getParams()[0]);
              }
            }
          }
        }
        setSize(lastAgg,field);
        setShardSize(lastAgg);
      }
      if (field.isNested()) {
        AggregationBuilder nestedBuilder=createNestedAggregation(field);
        if (insertFilterIfExistsAfter(lastAgg,groupBy,nestedBuilder,1)) {
          groupBy.remove(1);
        }
 else {
          nestedBuilder.subAggregation(lastAgg);
        }
        request.addAggregation(wrapNestedIfNeeded(nestedBuilder,field.isReverseNested()));
      }
 else       if (field.isChildren()) {
        AggregationBuilder childrenBuilder=createChildrenAggregation(field);
        if (insertFilterIfExistsAfter(lastAgg,groupBy,childrenBuilder,1)) {
          groupBy.remove(1);
        }
 else {
          childrenBuilder.subAggregation(lastAgg);
        }
        request.addAggregation(childrenBuilder);
      }
 else {
        request.addAggregation(lastAgg);
      }
      for (int i=1; i < groupBy.size(); i++) {
        field=groupBy.get(i);
        AggregationBuilder subAgg=getGroupAgg(field,select);
        setSize(subAgg,field);
        setShardSize(subAgg);
        if (field.isNested()) {
          AggregationBuilder nestedBuilder=createNestedAggregation(field);
          if (insertFilterIfExistsAfter(subAgg,groupBy,nestedBuilder,i + 1)) {
            groupBy.remove(i + 1);
            i++;
          }
 else {
            nestedBuilder.subAggregation(subAgg);
          }
          lastAgg.subAggregation(wrapNestedIfNeeded(nestedBuilder,field.isReverseNested()));
        }
 else         if (field.isChildren()) {
          AggregationBuilder childrenBuilder=createChildrenAggregation(field);
          if (insertFilterIfExistsAfter(subAgg,groupBy,childrenBuilder,i + 1)) {
            groupBy.remove(i + 1);
            i++;
          }
 else {
            childrenBuilder.subAggregation(subAgg);
          }
          lastAgg.subAggregation(childrenBuilder);
        }
 else {
          lastAgg.subAggregation(subAgg);
        }
        lastAgg=subAgg;
      }
    }
    explanFields(request,select.getFields(),lastAgg);
  }
  if (select.getGroupBys().size() < 1) {
    explanFields(request,select.getFields(),lastAgg);
  }
  Map<String,KVValue> groupMap=aggMaker.getGroupMap();
  if (select.getFields().size() > 0) {
    setFields(select.getFields());
  }
  if (lastAgg != null && select.getOrderBys().size() > 0) {
    for (    Order order : select.getOrderBys()) {
      KVValue temp=groupMap.get(order.getName());
      if (temp != null) {
        TermsAggregationBuilder termsBuilder=(TermsAggregationBuilder)temp.value;
switch (temp.key) {
case "COUNT":
          String orderName=order.getName();
        if (isAliasFiled(orderName)) {
          termsBuilder.order(BucketOrder.aggregation(orderName,isASC(order)));
        }
 else {
          termsBuilder.order(BucketOrder.count(isASC(order)));
        }
      break;
case "KEY":
    termsBuilder.order(BucketOrder.key(isASC(order)));
  request.addSort(order.getName(),SortOrder.valueOf(order.getType()));
break;
case "FIELD":
termsBuilder.order(BucketOrder.aggregation(order.getName(),isASC(order)));
break;
default :
throw new SqlParseException(order.getName() + " can not to order");
}
}
 else {
request.addSort(order.getName(),SortOrder.valueOf(order.getType()));
}
}
}
setLimitFromHint(this.select.getHints());
request.setSearchType(SearchType.DEFAULT);
updateRequestWithIndexAndRoutingOptions(select,request);
updateRequestWithHighlight(select,request);
updateRequestWithCollapse(select,request);
updateRequestWithPostFilter(select,request);
updateRequestWithStats(select,request);
updateRequestWithPreference(select,request);
SqlElasticSearchRequestBuilder sqlElasticRequestBuilder=new SqlElasticSearchRequestBuilder(request);
return sqlElasticRequestBuilder;
}
