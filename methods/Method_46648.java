@Override public void destroyContext(String groupId){
  try {
    fastStorage.clearGroup(groupId);
  }
 catch (  FastStorageException e) {
    if (e.getCode() == FastStorageException.EX_CODE_NON_GROUP) {
      return;
    }
    try {
      fastStorage.clearGroup(groupId);
    }
 catch (    FastStorageException e1) {
      throw new IllegalStateException(e1);
    }
  }
}
