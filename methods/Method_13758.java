/** 
 * {@inheritDoc}
 */
@Override public List<Recipient> findReadyToNotify(NotificationType type){
switch (type) {
case BACKUP:
    return repository.findReadyForBackup();
case REMIND:
  return repository.findReadyForRemind();
default :
throw new IllegalArgumentException();
}
}
