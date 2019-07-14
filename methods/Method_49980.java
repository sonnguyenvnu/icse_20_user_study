/** 
 * Make NotifyResp.Ind.
 */
private int makeNotifyResp(){
  if (mMessage == null) {
    mMessage=new ByteArrayOutputStream();
    mPosition=0;
  }
  appendOctet(PduHeaders.MESSAGE_TYPE);
  appendOctet(PduHeaders.MESSAGE_TYPE_NOTIFYRESP_IND);
  if (appendHeader(PduHeaders.TRANSACTION_ID) != PDU_COMPOSE_SUCCESS) {
    return PDU_COMPOSE_CONTENT_ERROR;
  }
  if (appendHeader(PduHeaders.MMS_VERSION) != PDU_COMPOSE_SUCCESS) {
    return PDU_COMPOSE_CONTENT_ERROR;
  }
  if (appendHeader(PduHeaders.STATUS) != PDU_COMPOSE_SUCCESS) {
    return PDU_COMPOSE_CONTENT_ERROR;
  }
  return PDU_COMPOSE_SUCCESS;
}
