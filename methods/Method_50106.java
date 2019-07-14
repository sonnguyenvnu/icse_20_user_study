private static ImmutableList<GraphqlError> errorsToProto(List<GraphQLError> errors){
  return errors.stream().map(error -> GraphqlError.newBuilder().setMessage(error.getMessage()).setType(ERROR_TYPE_MAP.getOrDefault(error.getErrorType(),com.google.api.graphql.ErrorType.UNKNOWN)).addAllLocations(error.getLocations().stream().map(location -> SourceLocation.newBuilder().setLine(location.getLine()).setColumn(location.getColumn()).build()).collect(ImmutableList.toImmutableList())).build()).collect(ImmutableList.toImmutableList());
}
