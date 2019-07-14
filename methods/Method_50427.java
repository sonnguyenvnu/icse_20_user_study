@Override public void executor(final HmilyTransactionEvent event){
  String transId=event.getHmilyTransaction().getTransId();
  executor.select(transId).execute(() -> {
    if (event.getType() == EventTypeEnum.SAVE.getCode()) {
      coordinatorService.save(event.getHmilyTransaction());
    }
 else     if (event.getType() == EventTypeEnum.UPDATE_PARTICIPANT.getCode()) {
      coordinatorService.updateParticipant(event.getHmilyTransaction());
    }
 else     if (event.getType() == EventTypeEnum.UPDATE_STATUS.getCode()) {
      final HmilyTransaction hmilyTransaction=event.getHmilyTransaction();
      coordinatorService.updateStatus(hmilyTransaction.getTransId(),hmilyTransaction.getStatus());
    }
 else     if (event.getType() == EventTypeEnum.DELETE.getCode()) {
      coordinatorService.remove(event.getHmilyTransaction().getTransId());
    }
    event.clear();
  }
);
}
