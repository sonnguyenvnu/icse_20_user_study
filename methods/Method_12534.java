public static LegacyEndpointConverter threaddump(){
  return new LegacyEndpointConverter(Endpoint.THREADDUMP,convertUsing(RESPONSE_TYPE_LIST,RESPONSE_TYPE_MAP,LegacyEndpointConverters::convertThreaddump));
}
