public CompensableTransactionImpl reconstruct(TransactionArchive transactionArchive){
  XidFactory xidFactory=this.beanFactory.getCompensableXidFactory();
  org.bytesoft.compensable.archive.TransactionArchive archive=(org.bytesoft.compensable.archive.TransactionArchive)transactionArchive;
  int transactionStatus=archive.getCompensableStatus();
  TransactionContext transactionContext=new TransactionContext();
  transactionContext.setCompensable(true);
  transactionContext.setStatefully(this.statefully);
  transactionContext.setCoordinator(archive.isCoordinator());
  transactionContext.setPropagated(archive.isPropagated());
  transactionContext.setCompensating(Status.STATUS_ACTIVE != transactionStatus && Status.STATUS_MARKED_ROLLBACK != transactionStatus);
  transactionContext.setRecoveried(true);
  transactionContext.setXid(xidFactory.createGlobalXid(archive.getXid().getGlobalTransactionId()));
  transactionContext.setPropagatedBy(transactionArchive.getPropagatedBy());
  transactionContext.setRecoveredTimes(transactionArchive.getRecoveredTimes());
  transactionContext.setCreatedTime(transactionArchive.getRecoveredAt());
  CompensableTransactionImpl transaction=new CompensableTransactionImpl(transactionContext);
  transaction.setBeanFactory(this.beanFactory);
  transaction.setTransactionVote(archive.getVote());
  transaction.setTransactionStatus(transactionStatus);
  transaction.setVariables(archive.getVariables());
  List<XAResourceArchive> participantList=archive.getRemoteResources();
  for (int i=0; i < participantList.size(); i++) {
    XAResourceArchive participantArchive=participantList.get(i);
    XAResourceDescriptor descriptor=participantArchive.getDescriptor();
    transaction.getParticipantArchiveList().add(participantArchive);
    if (RemoteResourceDescriptor.class.isInstance(descriptor)) {
      RemoteResourceDescriptor resourceDescriptor=(RemoteResourceDescriptor)descriptor;
      RemoteCoordinator remoteCoordinator=resourceDescriptor.getDelegate();
      RemoteSvc remoteSvc=CommonUtils.getRemoteSvc(remoteCoordinator.getRemoteNode());
      transaction.getParticipantArchiveMap().put(remoteSvc,participantArchive);
    }
  }
  List<CompensableArchive> compensableList=archive.getCompensableResourceList();
  for (int i=0; i < compensableList.size(); i++) {
    CompensableArchive compensableArchive=compensableList.get(i);
    transaction.getCompensableArchiveList().add(compensableArchive);
  }
  return transaction;
}
