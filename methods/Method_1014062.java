@Modified protected void modified(Map<String,@Nullable Object> configuration){
  Object valueEnabled=configuration.get(PROPERTY_ENABLED);
  if (valueEnabled != null) {
    enabled=Boolean.parseBoolean(valueEnabled.toString());
  }
  Object valueSendOptimisticUpdates=configuration.get(PROPERTY_SEND_OPTIMISTIC_UPDATES);
  if (valueSendOptimisticUpdates != null) {
    sendOptimisticUpdates=Boolean.parseBoolean(valueSendOptimisticUpdates.toString());
  }
}
