public static ActionInvocation send(final Device dev,String instanceID,String service,final String action,String... args){
  Service svc=dev.findService(ServiceId.valueOf("urn:upnp-org:serviceId:" + service));
  final String uuid=getUUID(dev);
  if (svc != null) {
    Action x=svc.getAction(action);
    String name=getFriendlyName(dev);
    boolean log=!action.equals("GetPositionInfo");
    if (x != null) {
      ActionInvocation a=new ActionInvocation(x);
      a.setInput("InstanceID",instanceID);
      for (int i=0; i < args.length; i+=2) {
        a.setInput(args[i],args[i + 1]);
      }
      if (log) {
        LOGGER.debug("Sending upnp {}.{} {} to {}[{}]",service,action,args,name,instanceID);
      }
      new ActionCallback(a,upnpService.getControlPoint()){
        @Override public void success(        ActionInvocation invocation){
          rendererMap.mark(uuid,ACTIVE,true);
        }
        @Override public void failure(        ActionInvocation invocation,        UpnpResponse operation,        String defaultMsg){
          LOGGER.error("Failed to send action \"{}\" to {}: {}",action,dev.getDetails().getFriendlyName(),defaultMsg);
          if (LOGGER.isTraceEnabled() && invocation != null && invocation.getFailure() != null) {
            LOGGER.trace("",invocation.getFailure());
          }
          rendererMap.mark(uuid,ACTIVE,false);
        }
      }
.run();
      if (log) {
        for (        ActionArgumentValue arg : a.getOutput()) {
          LOGGER.debug("Received from {}[{}]: {}={}",name,instanceID,arg.getArgument().getName(),arg.toString());
        }
      }
      return a;
    }
  }
 else {
    LOGGER.warn("Couldn't find UPnP service {} for device {} when trying perform action {}",service,dev.getDetails().getFriendlyName(),action);
  }
  return null;
}
