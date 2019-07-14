public int sendRequest(TLObject object,RequestDelegate completionBlock,QuickAckDelegate quickAckBlock,int flags){
  return sendRequest(object,completionBlock,quickAckBlock,null,flags,DEFAULT_DATACENTER_ID,ConnectionTypeGeneric,true);
}
