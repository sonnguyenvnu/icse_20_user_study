@SuppressWarnings({"rawtypes","unchecked"}) @Override public DataTransactionResult offer(final DataManipulator<?,?> valueContainer,final MergeFunction function){
  final Optional<DataProcessor> optional=DataUtil.getWildDataProcessor(valueContainer.getClass());
  if (optional.isPresent()) {
    return optional.get().set(this,valueContainer,checkNotNull(function));
  }
 else   if (this instanceof CustomDataHolderBridge) {
    return ((CustomDataHolderBridge)this).offerCustom(valueContainer,function);
  }
  return DataTransactionResult.failResult(valueContainer.getValues());
}
