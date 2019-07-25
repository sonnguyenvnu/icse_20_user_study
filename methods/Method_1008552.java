public Query parse(MultiMatchQueryBuilder.Type type,Map<String,Float> fieldNames,Object value,String minimumShouldMatch) throws IOException {
  Query result;
  if (fieldNames.size() == 1) {
    Map.Entry<String,Float> fieldBoost=fieldNames.entrySet().iterator().next();
    Float boostValue=fieldBoost.getValue();
    result=parseAndApply(type.matchQueryType(),fieldBoost.getKey(),value,minimumShouldMatch,boostValue);
  }
 else {
    final float tieBreaker=groupTieBreaker == null ? type.tieBreaker() : groupTieBreaker;
switch (type) {
case PHRASE:
case PHRASE_PREFIX:
case BEST_FIELDS:
case MOST_FIELDS:
      queryBuilder=new QueryBuilder(tieBreaker);
    break;
case CROSS_FIELDS:
  queryBuilder=new CrossFieldsQueryBuilder();
break;
default :
throw new IllegalStateException("No such type: " + type);
}
final List<? extends Query> queries=queryBuilder.buildGroupedQueries(type,fieldNames,value,minimumShouldMatch);
result=queryBuilder.combineGrouped(queries);
}
return result;
}
