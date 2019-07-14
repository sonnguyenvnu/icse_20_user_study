public String getStatusCodeReply(){
  flush();
  final byte[] resp=(byte[])readProtocolWithCheckingBroken();
  if (null == resp) {
    return null;
  }
 else {
    return SafeEncoder.encode(resp);
  }
}
