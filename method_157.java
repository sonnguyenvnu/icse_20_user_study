@Override protected final void _XXXXX_(AxisService service) throws AxisFault {
  E endpoint=createEndpoint();
  endpoint.init(this,service);
  if (endpoint.loadConfiguration(service)) {
    startEndpoint(endpoint);
    serviceEndpoints.add(endpoint);
  }
 else   if (globalEndpoint != null) {
    return;
  }
 else {
    throw new AxisFault("Service doesn't have configuration information for transport " + getTransportName());
  }
}