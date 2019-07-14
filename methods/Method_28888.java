private static String[] parseTargetHostAndSlot(String clusterRedirectResponse){
  String[] response=new String[3];
  String[] messageInfo=clusterRedirectResponse.split(" ");
  String[] targetHostAndPort=messageInfo[2].split(":");
  response[0]=messageInfo[1];
  response[1]=targetHostAndPort[0];
  response[2]=targetHostAndPort[1];
  return response;
}
