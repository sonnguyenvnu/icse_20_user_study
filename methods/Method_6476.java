private void delayRequestInfo(RequestInfo requestInfo){
  delayedRequestInfos.add(requestInfo);
  if (requestInfo.response != null) {
    requestInfo.response.disableFree=true;
  }
 else   if (requestInfo.responseWeb != null) {
    requestInfo.responseWeb.disableFree=true;
  }
 else   if (requestInfo.responseCdn != null) {
    requestInfo.responseCdn.disableFree=true;
  }
}
