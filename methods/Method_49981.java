/** 
 * Make Acknowledge.Ind.
 */
private int makeAckInd(){
  if (mMessage == null) {
    mMessage=new ByteArrayOutputStream();
    mPosition=0;
  }
  appendOctet(PduHeaders.MESSAGE_TYPE);
  appendOctet(PduHeaders.MESSAGE_TYPE_ACKNOWLEDGE_IND);
  if (appendHeader(PduHeaders.TRANSACTION_ID) != PDU_COMPOSE_SUCCESS) {
    return PDU_COMPOSE_CONTENT_ERROR;
  }
  if (appendHeader(PduHeaders.MMS_VERSION) != PDU_COMPOSE_SUCCESS) {
    return PDU_COMPOSE_CONTENT_ERROR;
  }
  appendHeader(PduHeaders.REPORT_ALLOWED);
  return PDU_COMPOSE_SUCCESS;
}
