private SofaTracerSpanContext saveSpanContextAndTags(Map<String,String> tags,Object oldInstanceMap){
  if (oldInstanceMap instanceof Map) {
    try {
      Map<String,String> contextMap=(Map<String,String>)oldInstanceMap;
      String traceId=this.getEmptyStringIfNull(contextMap,TracerCompatibleConstants.TRACE_ID_KEY);
      String rpcId=this.getEmptyStringIfNull(contextMap,TracerCompatibleConstants.RPC_ID_KEY);
      String bizBaggage=this.getEmptyStringIfNull(contextMap,TracerCompatibleConstants.PEN_ATTRS_KEY);
      String sysBaggage=this.getEmptyStringIfNull(contextMap,TracerCompatibleConstants.PEN_SYS_ATTRS_KEY);
      String callerApp=this.getEmptyStringIfNull(contextMap,TracerCompatibleConstants.CALLER_APP_KEY);
      String callerZone=this.getEmptyStringIfNull(contextMap,TracerCompatibleConstants.CALLER_ZONE_KEY);
      String callerIdc=this.getEmptyStringIfNull(contextMap,TracerCompatibleConstants.CALLER_IDC_KEY);
      String callerIp=this.getEmptyStringIfNull(contextMap,TracerCompatibleConstants.CALLER_IP_KEY);
      SofaTracerSpanContext spanContext=new SofaTracerSpanContext(traceId,rpcId);
      spanContext.setSampled(parseSampled(contextMap,spanContext));
      spanContext.deserializeBizBaggage(bizBaggage);
      spanContext.deserializeSysBaggage(sysBaggage);
      tags.put(RpcSpanTags.REMOTE_APP,callerApp);
      tags.put(RpcSpanTags.REMOTE_ZONE,callerZone);
      tags.put(RpcSpanTags.REMOTE_IDC,callerIdc);
      tags.put(RpcSpanTags.REMOTE_IP,callerIp);
      return spanContext;
    }
 catch (    Throwable throwable) {
      return null;
    }
  }
 else {
    return null;
  }
}
