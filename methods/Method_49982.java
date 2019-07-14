/** 
 * Make Send.req.
 */
private int makeSendReqPdu(){
  if (mMessage == null) {
    mMessage=new ByteArrayOutputStream();
    mPosition=0;
  }
  appendOctet(PduHeaders.MESSAGE_TYPE);
  appendOctet(PduHeaders.MESSAGE_TYPE_SEND_REQ);
  appendOctet(PduHeaders.TRANSACTION_ID);
  byte[] trid=mPduHeader.getTextString(PduHeaders.TRANSACTION_ID);
  if (trid == null) {
    throw new IllegalArgumentException("Transaction-ID is null.");
  }
  appendTextString(trid);
  if (appendHeader(PduHeaders.MMS_VERSION) != PDU_COMPOSE_SUCCESS) {
    return PDU_COMPOSE_CONTENT_ERROR;
  }
  appendHeader(PduHeaders.DATE);
  if (appendHeader(PduHeaders.FROM) != PDU_COMPOSE_SUCCESS) {
    return PDU_COMPOSE_CONTENT_ERROR;
  }
  boolean recipient=false;
  if (appendHeader(PduHeaders.TO) != PDU_COMPOSE_CONTENT_ERROR) {
    recipient=true;
  }
  if (appendHeader(PduHeaders.CC) != PDU_COMPOSE_CONTENT_ERROR) {
    recipient=true;
  }
  if (appendHeader(PduHeaders.BCC) != PDU_COMPOSE_CONTENT_ERROR) {
    recipient=true;
  }
  if (false == recipient) {
    return PDU_COMPOSE_CONTENT_ERROR;
  }
  appendHeader(PduHeaders.SUBJECT);
  appendHeader(PduHeaders.MESSAGE_CLASS);
  appendHeader(PduHeaders.EXPIRY);
  appendHeader(PduHeaders.PRIORITY);
  appendHeader(PduHeaders.DELIVERY_REPORT);
  appendHeader(PduHeaders.READ_REPORT);
  appendOctet(PduHeaders.CONTENT_TYPE);
  return makeMessageBody();
}
