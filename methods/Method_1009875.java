@Override public boolean supports(final Class<? extends DataManipulator<?,?>> holderClass){
  final Optional<DataProcessor<?,?>> optional=DataUtil.getWildProcessor(holderClass);
  return optional.isPresent() && optional.get().supports(this);
}
