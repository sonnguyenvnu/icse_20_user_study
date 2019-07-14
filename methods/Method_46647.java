@Override public DTXContext create(String groupId) throws TransactionException {
  try {
    fastStorage.initGroup(groupId);
  }
 catch (  FastStorageException e) {
    if (e.getCode() != FastStorageException.EX_CODE_REPEAT_GROUP) {
      throw new TransactionException(e);
    }
  }
  return get(groupId);
}
