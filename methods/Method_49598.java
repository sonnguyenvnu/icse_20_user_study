private static Query numericQuery(String key,Cmp relation,Number value){
switch (relation) {
case EQUAL:
    return AttributeUtil.isWholeNumber(value) ? LongPoint.newRangeQuery(key,value.longValue(),value.longValue()) : DoublePoint.newRangeQuery(key,value.doubleValue(),value.doubleValue());
case NOT_EQUAL:
  final BooleanQuery.Builder q=new BooleanQuery.Builder();
if (AttributeUtil.isWholeNumber(value)) {
  q.add(LongPoint.newRangeQuery(key,Long.MIN_VALUE,Math.addExact(value.longValue(),-1)),BooleanClause.Occur.SHOULD);
  q.add(LongPoint.newRangeQuery(key,Math.addExact(value.longValue(),1),Long.MAX_VALUE),BooleanClause.Occur.SHOULD);
}
 else {
  q.add(DoublePoint.newRangeQuery(key,Double.MIN_VALUE,DoublePoint.nextDown(value.doubleValue())),BooleanClause.Occur.SHOULD);
  q.add(DoublePoint.newRangeQuery(key,DoublePoint.nextUp(value.doubleValue()),Double.MAX_VALUE),BooleanClause.Occur.SHOULD);
}
return q.build();
case LESS_THAN:
return (AttributeUtil.isWholeNumber(value)) ? LongPoint.newRangeQuery(key,Long.MIN_VALUE,Math.addExact(value.longValue(),-1)) : DoublePoint.newRangeQuery(key,Double.MIN_VALUE,DoublePoint.nextDown(value.doubleValue()));
case LESS_THAN_EQUAL:
return (AttributeUtil.isWholeNumber(value)) ? LongPoint.newRangeQuery(key,Long.MIN_VALUE,value.longValue()) : DoublePoint.newRangeQuery(key,Double.MIN_VALUE,value.doubleValue());
case GREATER_THAN:
return (AttributeUtil.isWholeNumber(value)) ? LongPoint.newRangeQuery(key,Math.addExact(value.longValue(),1),Long.MAX_VALUE) : DoublePoint.newRangeQuery(key,DoublePoint.nextUp(value.doubleValue()),Double.MAX_VALUE);
case GREATER_THAN_EQUAL:
return (AttributeUtil.isWholeNumber(value)) ? LongPoint.newRangeQuery(key,value.longValue(),Long.MAX_VALUE) : DoublePoint.newRangeQuery(key,value.doubleValue(),Double.MAX_VALUE);
default :
throw new IllegalArgumentException("Unexpected relation: " + relation);
}
}
