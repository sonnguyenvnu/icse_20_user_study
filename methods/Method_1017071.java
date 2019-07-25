public static QueryResult error(DateRange range,String errorMessage,QueryTrace trace){
  return new QueryResult(range,Collections.emptyList(),Collections.singletonList(QueryError.fromMessage(errorMessage)),trace,ResultLimits.of(),0,Optional.empty());
}
