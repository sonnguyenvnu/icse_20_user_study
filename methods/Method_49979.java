/** 
 * Make ReadRec.Ind.
 */
private int makeReadRecInd(){
  if (mMessage == null) {
    mMessage=new ByteArrayOutputStream();
    mPosition=0;
  }
  appendOctet(PduHeaders.MESSAGE_TYPE);
  appendOctet(PduHeaders.MESSAGE_TYPE_READ_REC_IND);
  if (appendHeader(PduHeaders.MMS_VERSION) != PDU_COMPOSE_SUCCESS) {
    return PDU_COMPOSE_CONTENT_ERROR;
  }
  if (appendHeader(PduHeaders.MESSAGE_ID) != PDU_COMPOSE_SUCCESS) {
    return PDU_COMPOSE_CONTENT_ERROR;
  }
  if (appendHeader(PduHeaders.TO) != PDU_COMPOSE_SUCCESS) {
    return PDU_COMPOSE_CONTENT_ERROR;
  }
  if (appendHeader(PduHeaders.FROM) != PDU_COMPOSE_SUCCESS) {
    return PDU_COMPOSE_CONTENT_ERROR;
  }
  appendHeader(PduHeaders.DATE);
  if (appendHeader(PduHeaders.READ_STATUS) != PDU_COMPOSE_SUCCESS) {
    return PDU_COMPOSE_CONTENT_ERROR;
  }
  return PDU_COMPOSE_SUCCESS;
}
