/** 
 * A common method to retrieve a PDU from MMSC.
 * @param url The URL of the message which we are going to retrieve.
 * @return A byte array which contains the data of the PDU.If the status code is not correct, an IOException will be thrown.
 * @throws java.io.IOException if any error occurred on network interface oran HTTP error code(>=400) returned from the server.
 */
protected byte[] getPdu(final String url) throws IOException {
  if (url == null) {
    throw new IOException("Cannot establish route: url is null");
  }
  if (useWifi(mContext)) {
    return HttpUtils.httpConnection(mContext,SendingProgressTokenManager.NO_TOKEN,url,null,HttpUtils.HTTP_GET_METHOD,false,null,0);
  }
  return Utils.ensureRouteToMmsNetwork(mContext,url,mTransactionSettings.getProxyAddress(),new Utils.Task<byte[]>(){
    @Override public byte[] run() throws IOException {
      return HttpUtils.httpConnection(mContext,SendingProgressTokenManager.NO_TOKEN,url,null,HttpUtils.HTTP_GET_METHOD,mTransactionSettings.isProxySet(),mTransactionSettings.getProxyAddress(),mTransactionSettings.getProxyPort());
    }
  }
);
}
