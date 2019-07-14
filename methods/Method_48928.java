public static int adjustLimitForTxModifications(StandardJanusGraphTx tx,int uncoveredAndConditions,int limit){
  assert limit > 0 && limit <= 1000000000;
  assert uncoveredAndConditions >= 0;
  if (uncoveredAndConditions > 0) {
    final int maxMultiplier=Integer.MAX_VALUE / limit;
    limit=limit * Math.min(maxMultiplier,(int)Math.pow(2,uncoveredAndConditions));
  }
  if (tx.hasModifications())   limit+=Math.min(Integer.MAX_VALUE - limit,5);
  return limit;
}
