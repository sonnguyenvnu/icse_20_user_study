public static LegacyEndpointConverter httptrace(){
  return new LegacyEndpointConverter(Endpoint.HTTPTRACE,convertUsing(RESPONSE_TYPE_LIST_MAP,RESPONSE_TYPE_MAP,LegacyEndpointConverters::convertHttptrace));
}
