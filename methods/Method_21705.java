/** 
 * ?????
 * @param boolQuery
 * @param where
 * @param subQuery
 */
private void addSubQuery(BoolQueryBuilder boolQuery,Where where,QueryBuilder subQuery){
  if (where instanceof Condition) {
    Condition condition=(Condition)where;
    if (condition.isNested()) {
      boolean isNestedQuery=subQuery instanceof NestedQueryBuilder;
      InnerHitBuilder ihb=null;
      if (condition.getInnerHits() != null) {
        try (XContentParser parser=JsonXContent.jsonXContent.createParser(NamedXContentRegistry.EMPTY,LoggingDeprecationHandler.INSTANCE,condition.getInnerHits())){
          ihb=InnerHitBuilder.fromXContent(parser);
        }
 catch (        IOException e) {
          throw new IllegalArgumentException("couldn't parse inner_hits: " + e.getMessage(),e);
        }
      }
      if ("missing".equalsIgnoreCase(String.valueOf(condition.getValue())) && (condition.getOpear() == Condition.OPEAR.IS || condition.getOpear() == Condition.OPEAR.EQ)) {
        NestedQueryBuilder q=isNestedQuery ? (NestedQueryBuilder)subQuery : QueryBuilders.nestedQuery(condition.getNestedPath(),QueryBuilders.boolQuery().mustNot(subQuery),ScoreMode.None);
        if (ihb != null) {
          q.innerHit(ihb);
        }
        boolQuery.mustNot(q);
        return;
      }
      if (condition.getOpear() == Condition.OPEAR.NNESTED_COMPLEX) {
        if (ihb != null) {
          NestedQueryBuilder.class.cast(subQuery).innerHit(ihb);
        }
        boolQuery.mustNot(subQuery);
        return;
      }
      if (!isNestedQuery) {
        subQuery=QueryBuilders.nestedQuery(condition.getNestedPath(),subQuery,ScoreMode.None);
      }
      if (ihb != null) {
        ((NestedQueryBuilder)subQuery).innerHit(ihb);
      }
    }
 else     if (condition.isChildren()) {
      subQuery=JoinQueryBuilders.hasChildQuery(condition.getChildType(),subQuery,ScoreMode.None);
    }
  }
  if (where.getConn() == CONN.AND) {
    boolQuery.must(subQuery);
  }
 else {
    boolQuery.should(subQuery);
  }
}
