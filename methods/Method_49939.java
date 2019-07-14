private int getTransactionType(int msgType){
switch (msgType) {
case PduHeaders.MESSAGE_TYPE_NOTIFICATION_IND:
    return Transaction.RETRIEVE_TRANSACTION;
case PduHeaders.MESSAGE_TYPE_READ_REC_IND:
  return Transaction.READREC_TRANSACTION;
case PduHeaders.MESSAGE_TYPE_SEND_REQ:
return Transaction.SEND_TRANSACTION;
default :
Timber.w("Unrecognized MESSAGE_TYPE: " + msgType);
return -1;
}
}
