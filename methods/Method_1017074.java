@JsonCreator public static ResultLimits create(final Set<ResultLimit> limits){
  return new ResultLimits(limits);
}
