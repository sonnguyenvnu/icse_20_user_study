public void validate(final EventTypeBase eventType) throws NoSuchPartitionStrategyException, InvalidEventTypeException {
  final String partitionStrategy=eventType.getPartitionStrategy();
  if (!ALL_PARTITION_STRATEGIES.contains(partitionStrategy)) {
    throw new NoSuchPartitionStrategyException("partition strategy does not exist: " + partitionStrategy);
  }
 else   if (HASH_STRATEGY.equals(partitionStrategy) && eventType.getPartitionKeyFields().isEmpty()) {
    throw new InvalidEventTypeException("partition_key_fields field should be set for " + "partition strategy 'hash'");
  }
 else   if (USER_DEFINED_STRATEGY.equals(partitionStrategy) && UNDEFINED.equals(eventType.getCategory())) {
    throw new InvalidEventTypeException("'user_defined' partition strategy can't be used " + "for EventType of category 'undefined'");
  }
}
