private void updateFirstTableLimitIfNeeded(){
  if (requestBuilder.getJoinType() == SQLJoinTableSource.JoinType.LEFT_OUTER_JOIN) {
    Integer firstTableHintLimit=requestBuilder.getFirstTable().getHintLimit();
    int totalLimit=requestBuilder.getTotalLimit();
    if (firstTableHintLimit == null || firstTableHintLimit > totalLimit) {
      requestBuilder.getFirstTable().setHintLimit(totalLimit);
    }
  }
}
