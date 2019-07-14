private List<CommonAsyncTask> getNotificationTask(Context context,Intent intent,byte[] response){
  if (response.length == 0) {
    Timber.v("MmsReceivedReceiver.sendNotification blank response");
    return null;
  }
  if (getMmscInfoForReceptionAck() == null) {
    Timber.v("No MMSC information set, so no notification tasks will be able to complete");
    return null;
  }
  final GenericPdu pdu=(new PduParser(response,new MmsConfig.Overridden(new MmsConfig(context),null).getSupportMmsContentDisposition())).parse();
  if (pdu == null || !(pdu instanceof RetrieveConf)) {
    Timber.e("MmsReceivedReceiver.sendNotification failed to parse pdu");
    return null;
  }
  try {
    final NotificationInd ind=getNotificationInd(context,intent);
    final MmscInformation mmsc=getMmscInfoForReceptionAck();
    final TransactionSettings transactionSettings=new TransactionSettings(mmsc.mmscUrl,mmsc.mmsProxy,mmsc.proxyPort);
    final List<CommonAsyncTask> responseTasks=new ArrayList<>();
    responseTasks.add(new AcknowledgeIndTask(context,ind,transactionSettings,(RetrieveConf)pdu));
    responseTasks.add(new NotifyRespTask(context,ind,transactionSettings));
    return responseTasks;
  }
 catch (  MmsException e) {
    Timber.e(e,"error");
    return null;
  }
}
