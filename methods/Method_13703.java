/** 
 * @param sendBatchSmsRequest
 * @param accessKeyId
 * @param accessKeySecret
 * @throws ClientException
 * @return SendBatchSmsResponse
 */
@Override public SendBatchSmsResponse sendSmsBatchRequest(SendBatchSmsRequest sendBatchSmsRequest,String accessKeyId,String accessKeySecret) throws ClientException {
  EndpointManager.addSendBatchSmsRequest(sendBatchSmsRequest);
  return getHangZhouRegionClientProfile(accessKeyId,accessKeySecret).getAcsResponse(sendBatchSmsRequest);
}
