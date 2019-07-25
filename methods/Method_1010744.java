/** 
 * should be executed inside write action
 * @return true if new value was committed to model / false if nothing was changed
 */
public boolean commit(){
  getModelAccess().checkWriteAccess();
  if (!SNodeUtil.isAccessible(getSNode(),getContext().getRepository())) {
    return false;
  }
  if (myCommitInProgress) {
    return false;
  }
  myCommitInProgress=true;
  try {
    if (isTransactional()) {
      TransactionalModelAccessor transactionalModelAccessor=(TransactionalModelAccessor)myModelAccessor;
      boolean result=false;
      if (transactionalModelAccessor.hasValueToCommit()) {
        transactionalModelAccessor.commit();
        result=true;
      }
      getEditor().relayout();
      return result;
    }
  }
  finally {
    myCommitInProgress=false;
  }
  return false;
}
