public DeviceDetails provide(RemoteClientInfo info){
  if (info == null || info.getRequestHeaders().isEmpty())   return getDefaultDeviceDetails();
  for (  Key key : getHeaderDetails().keySet()) {
    List<String> headerValues;
    if ((headerValues=info.getRequestHeaders().get(key.getHeaderName())) == null)     continue;
    for (    String headerValue : headerValues) {
      if (key.isValuePatternMatch(headerValue))       return getHeaderDetails().get(key);
    }
  }
  return getDefaultDeviceDetails();
}
