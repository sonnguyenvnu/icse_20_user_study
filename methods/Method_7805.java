public boolean isSearchInProgress(){
  return reqId != 0 || channelReqId != 0;
}
