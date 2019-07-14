@Override public List<TransactionUnit> findTransactionUnitsFromGroup(String groupId) throws FastStorageException {
  Map<Object,Object> units=redisTemplate.opsForHash().entries(REDIS_GROUP_PREFIX + groupId);
  return units.entrySet().stream().filter(objectObjectEntry -> !objectObjectEntry.getKey().equals("root")).map(objectObjectEntry -> (TransactionUnit)objectObjectEntry.getValue()).collect(Collectors.toList());
}
