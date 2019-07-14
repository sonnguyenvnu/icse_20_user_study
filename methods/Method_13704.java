/** 
 * @param request
 * @param accessKeyId
 * @param accessKeySecret
 * @throws ClientException
 * @return QuerySendDetailsResponse
 */
@Override public QuerySendDetailsResponse querySendDetails(QuerySendDetailsRequest request,String accessKeyId,String accessKeySecret) throws ClientException {
  return getHangZhouRegionClientProfile(accessKeyId,accessKeySecret).getAcsResponse(request);
}
