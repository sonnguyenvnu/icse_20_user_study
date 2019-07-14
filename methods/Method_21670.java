private void fillBasicJoinRequestBuilder(JoinRequestBuilder requestBuilder) throws SqlParseException {
  fillTableInJoinRequestBuilder(requestBuilder.getFirstTable(),joinSelect.getFirstTable());
  fillTableInJoinRequestBuilder(requestBuilder.getSecondTable(),joinSelect.getSecondTable());
  requestBuilder.setJoinType(joinSelect.getJoinType());
  requestBuilder.setTotalLimit(joinSelect.getTotalLimit());
  updateRequestWithHints(requestBuilder);
}
