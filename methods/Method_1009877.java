@Override public boolean supports(final Key<?> key){
  final Optional<ValueProcessor<?,?>> optional=DataUtil.getWildValueProcessor(checkNotNull(key));
  return optional.isPresent() && optional.get().supports(this);
}
