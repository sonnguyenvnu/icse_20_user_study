private void sendNotifyRespInd(int status) throws MmsException, IOException {
  NotifyRespInd notifyRespInd=new NotifyRespInd(PduHeaders.CURRENT_MMS_VERSION,mNotificationInd.getTransactionId(),status);
  if (MmsConfig.getNotifyWapMMSC()) {
    sendPdu(new PduComposer(mContext,notifyRespInd).make(),mContentLocation);
  }
 else {
    sendPdu(new PduComposer(mContext,notifyRespInd).make());
  }
}
