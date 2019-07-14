private TokenForAlicom getTokenFromRemote(String messageType) throws ServerException, ClientException, ParseException {
  SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
  QueryTokenForMnsQueueRequest request=new QueryTokenForMnsQueueRequest();
  request.setAcceptFormat(FormatType.JSON);
  request.setMessageType(messageType);
  request.setOwnerId(ownerId);
  request.setProtocol(ProtocolType.HTTPS);
  request.setMethod(MethodType.POST);
  QueryTokenForMnsQueueResponse response=iAcsClient.getAcsResponse(request);
  String resultCode=response.getCode();
  if (resultCode != null && "OK".equals(resultCode)) {
    QueryTokenForMnsQueueResponse.MessageTokenDTO dto=response.getMessageTokenDTO();
    TokenForAlicom token=new TokenForAlicom();
    String timeStr=dto.getExpireTime();
    token.setMessageType(messageType);
    token.setExpireTime(df.parse(timeStr).getTime());
    token.setToken(dto.getSecurityToken());
    token.setTempAccessKeyId(dto.getAccessKeyId());
    token.setTempAccessKeySecret(dto.getAccessKeySecret());
    return token;
  }
 else {
    log.error("getTokenFromRemote_error,messageType:" + messageType + ",code:" + response.getCode() + ",message:" + response.getMessage());
    throw new ServerException(response.getCode(),response.getMessage());
  }
}
