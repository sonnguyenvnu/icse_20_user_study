public TokenForAlicom getTokenByMessageType(String messageType,String queueName,String mnsAccountEndpoint) throws ServerException, ClientException, ParseException {
  TokenForAlicom token=tokenMap.get(messageType);
  Long now=System.currentTimeMillis();
  if (token == null || (token.getExpireTime() - now) < bufferTime) {
synchronized (lock) {
      token=tokenMap.get(messageType);
      if (token == null || (token.getExpireTime() - now) < bufferTime) {
        TokenForAlicom oldToken=null;
        if (token != null) {
          oldToken=token;
        }
        token=getTokenFromRemote(messageType);
        CloudAccount account=new CloudAccount(token.getTempAccessKeyId(),token.getTempAccessKeySecret(),mnsAccountEndpoint,token.getToken());
        MNSClient client=account.getMNSClient();
        CloudQueue queue=client.getQueueRef(queueName);
        token.setClient(client);
        token.setQueue(queue);
        tokenMap.put(messageType,token);
        if (oldToken != null) {
          oldToken.closeClient();
        }
      }
    }
  }
  return token;
}
